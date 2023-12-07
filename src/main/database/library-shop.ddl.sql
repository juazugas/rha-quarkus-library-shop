--
-- PostgreSQL database dump
--
SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;
SET default_tablespace = '';
SET default_table_access_method = heap;
--
-- Name: book; Type: TABLE; Schema: public; Owner: quarkus
--
DROP TABLE IF EXISTS public.book;
DROP TABLE IF EXISTS public.book_authors;
DROP TABLE IF EXISTS public.ehlo_message;
--
-- Name: book; Type: TABLE; Schema: public; Owner: quarkus
--
CREATE TABLE public.book (
    price double precision NOT NULL,
    publication_year integer,
    id bigint NOT NULL,
    isbn character varying(255),
    title character varying(255)
);
--
-- Name: book_authors; Type: TABLE; Schema: public; Owner: quarkus
--
CREATE TABLE public.book_authors (
    book_id bigint NOT NULL,
    name character varying(255)
);
--
-- Name: book_seq; Type: SEQUENCE; Schema: public; Owner: quarkus
--
CREATE SEQUENCE public.book_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
--
-- Name: ehlo_message; Type: TABLE; Schema: public; Owner: quarkus
--
CREATE TABLE public.ehlo_message (
    createddt timestamp(6) with time zone,
    id bigint NOT NULL,
    message character varying(255)
);
--
-- Name: ehlo_message_seq; Type: SEQUENCE; Schema: public; Owner: quarkus
--
CREATE SEQUENCE public.ehlo_message_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
--
-- Data for Name: book; Type: TABLE DATA; Schema: public; Owner: quarkus
--
COPY public.book (price, publication_year, id, isbn, title) FROM stdin;
10	1979	1	0-330-25864-8	The Hitchhiker's Guide to the Galaxy
10	1992	2	0-593-59973-X	Snow Crash
10	1998	3	0-312-18087-X	Digital Fortress
\.
--
-- Data for Name: book_authors; Type: TABLE DATA; Schema: public; Owner: quarkus
--
COPY public.book_authors (book_id, name) FROM stdin;
1	Douglas Adams
2	Neal Stephenson
3	Dan Brown
\.
--
-- Data for Name: ehlo_message; Type: TABLE DATA; Schema: public; Owner: quarkus
--
COPY public.ehlo_message (createddt, id, message) FROM stdin;
\.
--
-- Name: book_seq; Type: SEQUENCE SET; Schema: public; Owner: quarkus
--
SELECT pg_catalog.setval('public.book_seq', 10, false);
--
-- Name: ehlo_message_seq; Type: SEQUENCE SET; Schema: public; Owner: quarkus
--
SELECT pg_catalog.setval('public.ehlo_message_seq', 1, false);
--
-- Name: book book_pkey; Type: CONSTRAINT; Schema: public; Owner: quarkus
--
ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (id);
--
-- Name: ehlo_message ehlo_message_pkey; Type: CONSTRAINT; Schema: public; Owner: quarkus
--
ALTER TABLE ONLY public.ehlo_message
    ADD CONSTRAINT ehlo_message_pkey PRIMARY KEY (id);
--
-- Name: book_authors fk406mbrpmr21u2tp17sy3yw1jm; Type: FK CONSTRAINT; Schema: public; Owner: quarkus
--
ALTER TABLE ONLY public.book_authors
    ADD CONSTRAINT fk406mbrpmr21u2tp17sy3yw1jm FOREIGN KEY (book_id) REFERENCES public.book(id);
--
-- PostgreSQL database dump complete
--