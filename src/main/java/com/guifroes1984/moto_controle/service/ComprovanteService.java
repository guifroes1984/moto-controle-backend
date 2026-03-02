package com.guifroes1984.moto_controle.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.guifroes1984.moto_controle.model.Comprovante;
import com.guifroes1984.moto_controle.model.Transacao;
import com.guifroes1984.moto_controle.repository.ComprovanteRepository;
import com.guifroes1984.moto_controle.repository.TransacaoRepository;

@Service
public class ComprovanteService {

	@Value("${file.upload-dir}")
	private String uploadDir;

	@Autowired
	private ComprovanteRepository comprovanteRepository;

	@Autowired
	private TransacaoRepository transacaoRepository;

	public Comprovante salvarComprovate(Long transacaoId, MultipartFile arquivo) {
		try {
			Transacao transacao = transacaoRepository.findById(transacaoId)
					.orElseThrow(() -> new RuntimeException("Transação não encontrada"));

			Path uploadPath = Paths.get(uploadDir);
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}

			String nomeOriginal = arquivo.getOriginalFilename();
			String extensao = nomeOriginal.substring(nomeOriginal.lastIndexOf("."));
			String nomeArquivo = UUID.randomUUID().toString() + extensao;

			Path filePath = uploadPath.resolve(nomeArquivo);

			Files.copy(arquivo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

			Comprovante comprovante = new Comprovante();
			comprovante.setNomeOriginal(nomeOriginal);
			comprovante.setNomeArquivo(nomeArquivo);
			comprovante.setTipoArquivo(arquivo.getContentType());
			comprovante.setTamanho(arquivo.getSize());
			comprovante.setCaminho("/uploads/" + nomeArquivo);
			comprovante.setDataUpload(LocalDateTime.now());
			comprovante.setTransacao(transacao);

			comprovanteRepository.findByTransacao(transacao).ifPresent(c -> {
				try {
					Files.deleteIfExists(Paths.get(uploadDir + "/" + c.getNomeArquivo()));
					comprovanteRepository.delete(c);
				} catch (IOException e) {
					throw new RuntimeException("Erro ao deletar comprovante antigo", e);
				}
			});

			return comprovanteRepository.save(comprovante);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao salvar comprovante", e);
		}
	}

}
