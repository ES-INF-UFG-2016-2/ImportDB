CREATE TABLE public.egresso
(
  id_egresso character varying(100),
  nome_egresso character varying(100) NOT NULL,
  tipo_doc_identidade character varying(50) NOT NULL,
  num_doc_identidade character varying(50) NOT NULL,
  data_nasc date NOT NULL,
  visivel_dados character varying(50) NOT NULL,
  data_ultm_atualizacao date,
  CONSTRAINT egresso_pkey PRIMARY KEY (tipo_doc_identidade, num_doc_identidade),
  CONSTRAINT egresso_visivel_dados_check CHECK (visivel_dados::text = ANY (ARRAY['Publico'::character varying, 'Privado'::character varying, 'Somente Egressos'::character varying]::text[]))
)

CREATE TABLE public.curso_ufg
(
  nome character varying(500) NOT NULL,
  nivel character varying(50) NOT NULL,
  dataCriacao date NOT NULL,
  ePresencial boolean NOT NULL,
  turno character varying(50),
  CONSTRAINT "checkNivel" CHECK (nivel::text = 'Bacharelado'::text OR nivel::text = 'Licenciatura'::text OR nivel::text = 'Aperfeicoamento'::text OR nivel::text = 'Especializacao'::text OR nivel::text = 'Mestrado'::text OR nivel::text = 'Doutorado'::text),
  CONSTRAINT "checkTurno" CHECK (turno::text = 'Matutino'::text OR turno::text = 'Vespertino'::text OR turno::text = 'Integral'::text)
)

CREATE TABLE public.avaliacao_curso
(
  data_avaliacao date NOT NULL,,
  satisfacao_escolha  character varying(50) NOT NULL,
  motivacao_escolha  character varying(50) NOT NULL,
  conceito_global integer NOT NULL,
  preparacao_curso character varying(50) NOT NULL,
  melhoria_comunicacao character varying(50) NOT NULL,
  capacid_fornecer_responsabilidae character varying(50) NOT NULL,
  capacid_melhorar_hab_especificas character varying(50) NOT NULL,
  CONSTRAINT avaliacao_curso_pkey PRIMARY KEY (id_avaliacao)
)

CREATE TABLE public.historico_ufg
(
  id_egresso character varying(100) NOT NULL,
  dataIngresso bigint  NOT NULL,
  dataConclusao bigint  NOT NULL,
  matriculaCurso bigint  NOT NULL,
  tituloTrabFinal character varying(500)
)

CREATE TABLE public.localizacao_geografica
(
  nome_cidade character varying(100) NOT NULL,
  nome_unid_federativa character varying(100) NOT NULL,
  nome_pais character varying(100) NOT NULL,
  sigla_unid_federativa character varying(20),
  latitude double precision NOT NULL,
  longitude double precision NOT NULL
)

CREATE TABLE public.residencia
(
  data_inicio date NOT NULL,
  data_fim date,
  endero character varying(300) NOT NULL,
  CONSTRAINT residencia_pkey PRIMARY KEY (data_inicio)
)

CREATE TABLE public.prog_academico
(
  tipo character varying(50) NOT NULL,
  data_inicio date NOT NULL,
  data_fim date NOT NULL,
  descricao character varying(500),
  CONSTRAINT "checkNivel" CHECK (tipo::text = 'Iniciacao Cientifica'::text OR tipo::text = 'Monitoria'::text OR tipo::text = 'Extensao'::text OR tipo::text = 'Intercambio'::text)
  CONSTRAINT "checkDataInicio" CHECK (data_inicio::date >= )
)