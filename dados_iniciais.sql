USE dbsigos;

/*Usuário default*/
INSERT INTO `usuarios` (`id`,`password`,`role`,`username`) VALUES (1,'$2a$10$XO17AsZE6pi4CgTVYhdPHeUCKqyxXoGrDrhPh6UjorPwLizO.gxGa','ROLE_ADMIN','admin');
INSERT INTO `usuarios` (`id`, `matricula_tecnico`, `password`,`role`,`username`) VALUES (2, '100','$2a$10$XO17AsZE6pi4CgTVYhdPHeUCKqyxXoGrDrhPh6UjorPwLizO.gxGa','ROLE_USER','tecnico');

/*Tipos de contrato*/
INSERT INTO `tipo_contrato` (`id`, `descricao`) VALUES (1, 'Nenhum');
INSERT INTO `tipo_contrato` (`id`, `descricao`) VALUES (2, 'Manutenção preventiva');

/*Endereço cliente*/
INSERT INTO `endereco_cliente` 
	(`id`, `bairro`, `cep`, `cidade`, `complemento`, `email`, `fone`, `logradouro`, `nome_contato`, `numero`, `uf`)
VALUES
(1, 'Jardim Ana Lúcia', '74315020', 'Goiânia', 'QD 23, Lt 6', 'gilsonalves.toc86@gmail.com', '62 99999-9999', 'Avenida Araxá', 'Gilson Alves', '123', 'GO');
INSERT INTO `endereco_cliente` 
	(`id`, `bairro`, `cep`, `cidade`, `complemento`, `email`, `fone`, `logradouro`, `nome_contato`, `numero`, `uf`)
VALUES
(2, 'Jardim Ana Lúcia', '74315020', 'Goiânia', 'QD 23, Lt 6', 'gilsonalves.toc86@gmail.com', '62 99999-9999', 'Avenida Araxá', 'Gilson Alves', '123', 'GO');

/*cliente*/
INSERT INTO `cliente`  
	(`id`,`cnpj`,`inscr_estadual`, `inscr_municipal`,`nome_fantasia`,`razao_social`,`endereco_id`,`tipo_contrato_id`)
VALUES
	(1, '12345678991234', '', '', 'CLIENTE 1 LTDA', 'CLIENTE 1', 1, 1);
INSERT INTO `cliente`  
	(`id`,`cnpj`,`inscr_estadual`, `inscr_municipal`,`nome_fantasia`,`razao_social`,`endereco_id`,`tipo_contrato_id`)
VALUES
	(2, '12345678991234', '', '', 'CLIENTE 2 LTDA', 'CLIENTE 2', 2, 1);

/*modelo equipamenento*/
INSERT INTO `modelo_equipamento` (`id`, `descricao`) VALUES(1, 'Print Point II');

/*tipo equipamento*/
INSERT INTO `tipo_equipamento` (`id`, `descricao`) VALUES(1, 'REP Fiscal');

/*equipamento*/

INSERT INTO `equipamento` 
	(`id`, `data_cadastro`, `numero_serie`, `cliente_id`, `modelo_equipamento_id`, `tipo_equipamento_id`, `manutencao`)
VALUES
	(1, '2017-01-01', '1111', 1, 1, 1, false);
    
INSERT INTO `equipamento` 
	(`id`, `data_cadastro`, `numero_serie`, `cliente_id`, `modelo_equipamento_id`, `tipo_equipamento_id`, `manutencao`)
    VALUES
	(2, '2017-01-01', '2222', 2, 1, 1, false);
INSERT INTO `equipamento` 
	(`id`, `data_cadastro`, `numero_serie`, `cliente_id`, `modelo_equipamento_id`, `tipo_equipamento_id`, `manutencao`)
VALUES
	(3, '2017-01-01', '3333', 2, 1, 1, false);

INSERT INTO `dbsigos`.`tecnico`
(`id`,
`matricula`,
`nome`,
`telefone`)
VALUES
(1, '100','Thiago Teles', '(99) 99999-9999');
INSERT INTO `dbsigos`.`tecnico`
(`id`,
`matricula`,
`nome`,
`telefone`)
VALUES
(2, '101','Marcos Silva', '(99) 99999-9999');
INSERT INTO `dbsigos`.`tecnico`
(`id`,
`matricula`,
`nome`,
`telefone`)
VALUES
(3, '102','Rafael Farias', '(99) 99999-9999');

INSERT INTO `dbsigos`.`servico`
(`id`,
`descricao`,
`valor`)
VALUES
(1, 'Revisão e limpeza', 200);

INSERT INTO `dbsigos`.`servico`
(`id`,
`descricao`,
`valor`)
VALUES
(2, 'Troca da placa principal', 1280);
