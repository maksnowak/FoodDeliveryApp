--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1 (Debian 16.1-1.pgdg120+1)
-- Dumped by pg_dump version 16.1 (Ubuntu 16.1-1.pgdg22.04+1)

-- Started on 2023-12-07 13:24:26 CET

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

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

--CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 3501 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 16389)
-- Name: account_types; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account_types (
    type_id integer NOT NULL,
    name character varying(20) NOT NULL
);


ALTER TABLE public.account_types OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16388)
-- Name: account_types_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.account_types ALTER COLUMN type_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.account_types_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 218 (class 1259 OID 16395)
-- Name: accounts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.accounts (
    account_id integer NOT NULL,
    email character varying(30) NOT NULL,
    password character varying(50) NOT NULL,
    type integer NOT NULL,
    name character varying(20),
    surname character varying(20)
);


ALTER TABLE public.accounts OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16394)
-- Name: accounts_account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.accounts ALTER COLUMN account_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.accounts_account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 220 (class 1259 OID 16401)
-- Name: baskets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.baskets (
    id integer NOT NULL,
    customer integer NOT NULL,
    dish_id integer NOT NULL
);


ALTER TABLE public.baskets OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16400)
-- Name: baskets_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.baskets ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.baskets_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 222 (class 1259 OID 16407)
-- Name: complaints; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.complaints (
    complaint_id integer NOT NULL,
    order_id integer NOT NULL,
    description character varying(500) NOT NULL,
    is_open boolean DEFAULT true NOT NULL
);


ALTER TABLE public.complaints OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16406)
-- Name: complaints_complaint_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.complaints ALTER COLUMN complaint_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.complaints_complaint_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 224 (class 1259 OID 16416)
-- Name: discounts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.discounts (
    discount_id integer NOT NULL,
    code character varying(10) NOT NULL,
    discount numeric(4,2) NOT NULL
);


ALTER TABLE public.discounts OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16415)
-- Name: discounts_discount_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.discounts ALTER COLUMN discount_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.discounts_discount_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 226 (class 1259 OID 16422)
-- Name: dish_types; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dish_types (
    type_id integer NOT NULL,
    name character varying(20) NOT NULL
);


ALTER TABLE public.dish_types OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16421)
-- Name: dish_types_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.dish_types ALTER COLUMN type_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.dish_types_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 228 (class 1259 OID 16428)
-- Name: dishes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dishes (
    dish_id integer NOT NULL,
    name character varying(60) NOT NULL,
    restaurant_id integer NOT NULL,
    type_id integer NOT NULL,
    vegetarian boolean,
    price numeric(5,2) NOT NULL,
    kcal numeric(4,0)
);


ALTER TABLE public.dishes OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 16427)
-- Name: dishes_dish_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.dishes ALTER COLUMN dish_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.dishes_dish_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 230 (class 1259 OID 16434)
-- Name: favorites; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.favorites (
    id integer NOT NULL,
    dish_id integer NOT NULL,
    customer integer NOT NULL
);


ALTER TABLE public.favorites OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 16433)
-- Name: favorites_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.favorites ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.favorites_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 232 (class 1259 OID 16440)
-- Name: ordered_dishes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ordered_dishes (
    id integer NOT NULL,
    order_id integer,
    dish_id integer
);


ALTER TABLE public.ordered_dishes OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 16439)
-- Name: ordered_dishes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.ordered_dishes ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.ordered_dishes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 234 (class 1259 OID 16446)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    order_id integer NOT NULL,
    status integer NOT NULL,
    customer integer NOT NULL,
    total numeric(7,2) NOT NULL,
    payment_method integer NOT NULL,
    street character(40) NOT NULL,
    street_number integer NOT NULL,
    apartment integer,
    city character varying(40) NOT NULL,
    discount integer,
    tip numeric(7,2)
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 16445)
-- Name: orders_order_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.orders ALTER COLUMN order_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.orders_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 236 (class 1259 OID 16452)
-- Name: payment_methods; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.payment_methods (
    method_id integer NOT NULL,
    card_number numeric(16,0) NOT NULL,
    expiry_date date NOT NULL,
    cvv numeric(3,0) NOT NULL,
    customer integer NOT NULL
);


ALTER TABLE public.payment_methods OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 16451)
-- Name: payment_methods_method_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.payment_methods ALTER COLUMN method_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.payment_methods_method_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 238 (class 1259 OID 16458)
-- Name: restaurants; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.restaurants (
    restaurant_id integer NOT NULL,
    name character varying(60) NOT NULL,
    opens_weekdays time without time zone,
    closes_weekdays time without time zone,
    opens_weekends time without time zone,
    closes_weekends time without time zone
);


ALTER TABLE public.restaurants OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 16457)
-- Name: restaurants_restaurant_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.restaurants ALTER COLUMN restaurant_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.restaurants_restaurant_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 240 (class 1259 OID 16464)
-- Name: reviews; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reviews (
    review_id integer NOT NULL,
    resturant_id integer NOT NULL,
    customer integer NOT NULL,
    stars integer NOT NULL,
    description character varying(500)
);


ALTER TABLE public.reviews OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 16463)
-- Name: reviews_review_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.reviews ALTER COLUMN review_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reviews_review_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 242 (class 1259 OID 16472)
-- Name: statuses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.statuses (
    status_id integer NOT NULL,
    name character varying(20) NOT NULL
);


ALTER TABLE public.statuses OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 16471)
-- Name: statuses_status_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.statuses ALTER COLUMN status_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.statuses_status_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 244 (class 1259 OID 16478)
-- Name: workers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.workers (
    id integer NOT NULL,
    worker integer NOT NULL,
    restaurant_id integer
);


ALTER TABLE public.workers OWNER TO postgres;

--
-- TOC entry 243 (class 1259 OID 16477)
-- Name: workers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.workers ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.workers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3467 (class 0 OID 16389)
-- Dependencies: 216
-- Data for Name: account_types; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.account_types OVERRIDING SYSTEM VALUE VALUES (1, 'Klient');
INSERT INTO public.account_types OVERRIDING SYSTEM VALUE VALUES (2, 'Pracownik');


--
-- TOC entry 3469 (class 0 OID 16395)
-- Dependencies: 218
-- Data for Name: accounts; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.accounts OVERRIDING SYSTEM VALUE VALUES (1, 'admin@example.com', 'admin', 2, NULL, NULL);


--
-- TOC entry 3471 (class 0 OID 16401)
-- Dependencies: 220
-- Data for Name: baskets; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3473 (class 0 OID 16407)
-- Dependencies: 222
-- Data for Name: complaints; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3475 (class 0 OID 16416)
-- Dependencies: 224
-- Data for Name: discounts; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.discounts OVERRIDING SYSTEM VALUE VALUES (1, 'MINUS20', 20.00);


--
-- TOC entry 3477 (class 0 OID 16422)
-- Dependencies: 226
-- Data for Name: dish_types; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.dish_types OVERRIDING SYSTEM VALUE VALUES (1, 'Przystawka');
INSERT INTO public.dish_types OVERRIDING SYSTEM VALUE VALUES (2, 'Zupa');
INSERT INTO public.dish_types OVERRIDING SYSTEM VALUE VALUES (3, 'Danie główne');
INSERT INTO public.dish_types OVERRIDING SYSTEM VALUE VALUES (4, 'Deser');
INSERT INTO public.dish_types OVERRIDING SYSTEM VALUE VALUES (5, 'Dodatki');
INSERT INTO public.dish_types OVERRIDING SYSTEM VALUE VALUES (6, 'Sałatki');
INSERT INTO public.dish_types OVERRIDING SYSTEM VALUE VALUES (7, 'Napoje');


--
-- TOC entry 3479 (class 0 OID 16428)
-- Dependencies: 228
-- Data for Name: dishes; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (1, 'Zupa pho', 1, 2, false, 15.00, 350);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (2, 'Kurczak w cieście', 1, 3, false, 20.00, 500);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (3, 'Kurczak chrupiący', 1, 3, false, 21.00, 450);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (4, 'Kurczak po tajsku', 1, 3, false, 22.00, 450);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (5, 'Makaron sojowy z kurczakiem', 1, 3, false, 25.00, 550);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (6, 'Makaron sojowy z wieprzowiną', 1, 3, false, 28.00, 600);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (7, 'Frytki', 1, 5, true, 7.00, 200);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (8, 'Ryż smażony', 1, 3, true, 18.00, 320);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (9, 'Woda', 1, 7, NULL, 3.00, 0);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (10, 'Herbata', 1, 7, NULL, 5.00, 10);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (11, 'Cola', 1, 7, NULL, 6.00, 75);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (12, 'Pizza margherita', 2, 3, true, 20.00, 900);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (13, 'Pizza capriciosa', 2, 3, false, 22.00, 920);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (14, 'Pizza z szynką parmeńską', 2, 3, false, 24.00, 1000);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (15, 'Pizza wiejska', 2, 3, false, 25.00, 1110);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (16, 'Pizza pepperoni', 2, 3, false, 23.00, 1200);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (17, 'Pizza hawajska', 2, 3, false, 24.00, 1300);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (18, 'Pizza napoletana', 2, 3, true, 23.00, 1200);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (19, 'Sos czosnkowy', 2, 5, NULL, 4.00, 100);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (20, 'Sos pomidorowy', 2, 5, NULL, 4.00, 100);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (21, 'Cola', 2, 7, NULL, 6.00, 75);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (22, 'Sprite', 2, 7, NULL, 6.00, 80);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (23, 'Woda', 2, 7, NULL, 3.00, 0);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (24, 'Fanta', 2, 7, NULL, 6.00, 90);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (25, 'Sałatka cezar', 3, 6, false, 15.00, 250);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (26, 'Bruschetta', 3, 1, true, 12.00, 200);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (27, 'Foccacia', 3, 1, true, 12.00, 170);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (28, 'Spaghetti carbonara', 3, 3, false, 20.00, 400);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (29, 'Risotto z krewetkami', 3, 3, false, 25.00, 500);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (30, 'Owoce morza', 3, 3, false, 35.00, 420);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (31, 'Pizza margherita', 3, 3, true, 28.00, 910);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (32, 'Pizza pepperoni', 3, 3, false, 30.00, 1180);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (33, 'Tiramisu', 3, 4, true, 15.00, 700);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (34, 'Cola', 3, 7, NULL, 10.00, 75);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (35, 'Lemoniada', 3, 7, NULL, 8.00, 40);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (36, 'Kawa', 3, 7, NULL, 12.00, 100);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (37, 'Wino', 3, 7, NULL, 20.00, 120);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (38, 'Zupa pomidorowa', 4, 2, false, 7.00, 300);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (39, 'Kotlet mielony', 4, 3, false, 11.00, 600);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (40, 'Kotlet schabowy', 4, 3, false, 12.00, 640);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (41, 'Gołąbki', 4, 3, false, 14.00, 700);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (42, 'Ziemniaki', 4, 5, true, 4.00, 180);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (43, 'Ryż', 4, 5, true, 5.00, 150);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (44, 'Kopytka', 4, 3, true, 9.00, 700);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (45, 'Pierogi ruskie', 4, 3, true, 10.00, 1000);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (46, 'Pierogi z mięsem', 4, 3, false, 11.00, 900);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (47, 'Woda', 4, 7, NULL, 3.00, 0);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (48, 'Kompot', 4, 7, NULL, 3.50, 30);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (49, 'Chaczapuri adżaruli', 5, 1, true, 20.00, 400);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (50, 'Chaczapuri imerula', 5, 1, true, 20.00, 450);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (51, 'Lobiani', 5, 1, true, 22.00, 500);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (52, 'Kupdari', 5, 1, false, 25.00, 550);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (53, 'Chinkali z mięsem', 5, 3, false, 28.00, 700);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (54, 'Chinkali z serem', 5, 3, true, 26.00, 850);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (55, 'Tolma', 5, 1, false, 24.00, 400);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (56, 'Pielmieni', 5, 3, false, 27.00, 720);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (57, 'Czanachi', 5, 3, false, 32.00, 600);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (58, 'Czaszuszuli', 5, 3, false, 30.00, 700);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (59, 'Lemoniada', 5, 7, NULL, 10.00, 40);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (60, 'Woda', 5, 7, NULL, 5.00, 0);
INSERT INTO public.dishes OVERRIDING SYSTEM VALUE VALUES (61, 'Piwo', 5, 7, NULL, 13.00, 150);


--
-- TOC entry 3481 (class 0 OID 16434)
-- Dependencies: 230
-- Data for Name: favorites; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3483 (class 0 OID 16440)
-- Dependencies: 232
-- Data for Name: ordered_dishes; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3485 (class 0 OID 16446)
-- Dependencies: 234
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3487 (class 0 OID 16452)
-- Dependencies: 236
-- Data for Name: payment_methods; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3489 (class 0 OID 16458)
-- Dependencies: 238
-- Data for Name: restaurants; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.restaurants OVERRIDING SYSTEM VALUE VALUES (1, 'Smaki Azji', '10:00:00', '21:00:00', '10:00:00', '23:00:00');
INSERT INTO public.restaurants OVERRIDING SYSTEM VALUE VALUES (2, 'Pizzeria', '12:00:00', '22:00:00', '12:00:00', '23:30:00');
INSERT INTO public.restaurants OVERRIDING SYSTEM VALUE VALUES (3, 'Włoska', '11:00:00', '20:00:00', '12:00:00', '23:00:00');
INSERT INTO public.restaurants OVERRIDING SYSTEM VALUE VALUES (4, 'Bar mleczny', '07:00:00', '21:00:00', NULL, NULL);
INSERT INTO public.restaurants OVERRIDING SYSTEM VALUE VALUES (5, 'Restauracja gruzińska', '09:00:00', '20:00:00', '10:00:00', '22:00:00');


--
-- TOC entry 3491 (class 0 OID 16464)
-- Dependencies: 240
-- Data for Name: reviews; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3493 (class 0 OID 16472)
-- Dependencies: 242
-- Data for Name: statuses; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.statuses OVERRIDING SYSTEM VALUE VALUES (1, 'Złożone');
INSERT INTO public.statuses OVERRIDING SYSTEM VALUE VALUES (2, 'Przyjęte');
INSERT INTO public.statuses OVERRIDING SYSTEM VALUE VALUES (3, 'Odrzucone');
INSERT INTO public.statuses OVERRIDING SYSTEM VALUE VALUES (4, 'W trakcie realizacji');
INSERT INTO public.statuses OVERRIDING SYSTEM VALUE VALUES (5, 'Przekazane dostawcy');
INSERT INTO public.statuses OVERRIDING SYSTEM VALUE VALUES (6, 'Dostarczone');
INSERT INTO public.statuses OVERRIDING SYSTEM VALUE VALUES (7, 'Anulowane');
INSERT INTO public.statuses OVERRIDING SYSTEM VALUE VALUES (8, 'Zareklamowane');


--
-- TOC entry 3495 (class 0 OID 16478)
-- Dependencies: 244
-- Data for Name: workers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.workers OVERRIDING SYSTEM VALUE VALUES (1, 1, 1);
INSERT INTO public.workers OVERRIDING SYSTEM VALUE VALUES (2, 1, 2);
INSERT INTO public.workers OVERRIDING SYSTEM VALUE VALUES (3, 1, 3);
INSERT INTO public.workers OVERRIDING SYSTEM VALUE VALUES (4, 1, 4);
INSERT INTO public.workers OVERRIDING SYSTEM VALUE VALUES (5, 1, 5);


--
-- TOC entry 3502 (class 0 OID 0)
-- Dependencies: 215
-- Name: account_types_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.account_types_type_id_seq', 3, false);


--
-- TOC entry 3503 (class 0 OID 0)
-- Dependencies: 217
-- Name: accounts_account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.accounts_account_id_seq', 2, false);


--
-- TOC entry 3504 (class 0 OID 0)
-- Dependencies: 219
-- Name: baskets_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.baskets_id_seq', 1, false);


--
-- TOC entry 3505 (class 0 OID 0)
-- Dependencies: 221
-- Name: complaints_complaint_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.complaints_complaint_id_seq', 1, false);


--
-- TOC entry 3506 (class 0 OID 0)
-- Dependencies: 223
-- Name: discounts_discount_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.discounts_discount_id_seq', 2, false);


--
-- TOC entry 3507 (class 0 OID 0)
-- Dependencies: 225
-- Name: dish_types_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.dish_types_type_id_seq', 8, false);


--
-- TOC entry 3508 (class 0 OID 0)
-- Dependencies: 227
-- Name: dishes_dish_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.dishes_dish_id_seq', 62, false);


--
-- TOC entry 3509 (class 0 OID 0)
-- Dependencies: 229
-- Name: favorites_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.favorites_id_seq', 1, false);


--
-- TOC entry 3510 (class 0 OID 0)
-- Dependencies: 231
-- Name: ordered_dishes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ordered_dishes_id_seq', 1, false);


--
-- TOC entry 3511 (class 0 OID 0)
-- Dependencies: 233
-- Name: orders_order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_order_id_seq', 1, false);


--
-- TOC entry 3512 (class 0 OID 0)
-- Dependencies: 235
-- Name: payment_methods_method_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.payment_methods_method_id_seq', 1, false);


--
-- TOC entry 3513 (class 0 OID 0)
-- Dependencies: 237
-- Name: restaurants_restaurant_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.restaurants_restaurant_id_seq', 6, false);


--
-- TOC entry 3514 (class 0 OID 0)
-- Dependencies: 239
-- Name: reviews_review_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reviews_review_id_seq', 1, false);


--
-- TOC entry 3515 (class 0 OID 0)
-- Dependencies: 241
-- Name: statuses_status_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.statuses_status_id_seq', 9, false);


--
-- TOC entry 3516 (class 0 OID 0)
-- Dependencies: 243
-- Name: workers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.workers_id_seq', 6, false);


--
-- TOC entry 3275 (class 2606 OID 16393)
-- Name: account_types account_types_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account_types
    ADD CONSTRAINT account_types_pkey PRIMARY KEY (type_id);


--
-- TOC entry 3277 (class 2606 OID 16399)
-- Name: accounts accounts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT accounts_pkey PRIMARY KEY (account_id);


--
-- TOC entry 3279 (class 2606 OID 16405)
-- Name: baskets baskets_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.baskets
    ADD CONSTRAINT baskets_pkey PRIMARY KEY (id);


--
-- TOC entry 3281 (class 2606 OID 16414)
-- Name: complaints complaints_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.complaints
    ADD CONSTRAINT complaints_pkey PRIMARY KEY (complaint_id);


--
-- TOC entry 3283 (class 2606 OID 16420)
-- Name: discounts discounts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.discounts
    ADD CONSTRAINT discounts_pkey PRIMARY KEY (discount_id);


--
-- TOC entry 3285 (class 2606 OID 16426)
-- Name: dish_types dish_types_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dish_types
    ADD CONSTRAINT dish_types_pkey PRIMARY KEY (type_id);


--
-- TOC entry 3287 (class 2606 OID 16432)
-- Name: dishes dishes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dishes
    ADD CONSTRAINT dishes_pkey PRIMARY KEY (dish_id);


--
-- TOC entry 3289 (class 2606 OID 16438)
-- Name: favorites favorites_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favorites
    ADD CONSTRAINT favorites_pkey PRIMARY KEY (id);


--
-- TOC entry 3291 (class 2606 OID 16444)
-- Name: ordered_dishes ordered_dishes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordered_dishes
    ADD CONSTRAINT ordered_dishes_pkey PRIMARY KEY (id);


--
-- TOC entry 3293 (class 2606 OID 16450)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (order_id);


--
-- TOC entry 3295 (class 2606 OID 16456)
-- Name: payment_methods payment_methods_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment_methods
    ADD CONSTRAINT payment_methods_pkey PRIMARY KEY (method_id);


--
-- TOC entry 3297 (class 2606 OID 16462)
-- Name: restaurants restaurants_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.restaurants
    ADD CONSTRAINT restaurants_pkey PRIMARY KEY (restaurant_id);


--
-- TOC entry 3299 (class 2606 OID 16470)
-- Name: reviews reviews_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_pkey PRIMARY KEY (review_id);


--
-- TOC entry 3301 (class 2606 OID 16476)
-- Name: statuses statuses_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.statuses
    ADD CONSTRAINT statuses_pkey PRIMARY KEY (status_id);


--
-- TOC entry 3303 (class 2606 OID 16482)
-- Name: workers workers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workers
    ADD CONSTRAINT workers_pkey PRIMARY KEY (id);


--
-- TOC entry 3305 (class 2606 OID 16488)
-- Name: baskets customer_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.baskets
    ADD CONSTRAINT customer_fk FOREIGN KEY (customer) REFERENCES public.accounts(account_id);


--
-- TOC entry 3310 (class 2606 OID 16513)
-- Name: favorites customer_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favorites
    ADD CONSTRAINT customer_fk FOREIGN KEY (customer) REFERENCES public.accounts(account_id);


--
-- TOC entry 3314 (class 2606 OID 16533)
-- Name: orders customer_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT customer_fk FOREIGN KEY (customer) REFERENCES public.accounts(account_id);


--
-- TOC entry 3318 (class 2606 OID 16553)
-- Name: payment_methods customer_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment_methods
    ADD CONSTRAINT customer_fk FOREIGN KEY (customer) REFERENCES public.accounts(account_id);


--
-- TOC entry 3319 (class 2606 OID 16558)
-- Name: reviews customer_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT customer_fk FOREIGN KEY (customer) REFERENCES public.accounts(account_id);


--
-- TOC entry 3315 (class 2606 OID 16538)
-- Name: orders discount_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT discount_id FOREIGN KEY (discount) REFERENCES public.discounts(discount_id);


--
-- TOC entry 3306 (class 2606 OID 16493)
-- Name: baskets dish_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.baskets
    ADD CONSTRAINT dish_fk FOREIGN KEY (dish_id) REFERENCES public.dishes(dish_id);


--
-- TOC entry 3311 (class 2606 OID 16518)
-- Name: favorites dish_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favorites
    ADD CONSTRAINT dish_fk FOREIGN KEY (dish_id) REFERENCES public.dishes(dish_id);


--
-- TOC entry 3312 (class 2606 OID 16523)
-- Name: ordered_dishes dish_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordered_dishes
    ADD CONSTRAINT dish_fk FOREIGN KEY (dish_id) REFERENCES public.dishes(dish_id);


--
-- TOC entry 3307 (class 2606 OID 16498)
-- Name: complaints order_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.complaints
    ADD CONSTRAINT order_fk FOREIGN KEY (order_id) REFERENCES public.orders(order_id);


--
-- TOC entry 3313 (class 2606 OID 16528)
-- Name: ordered_dishes order_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ordered_dishes
    ADD CONSTRAINT order_fk FOREIGN KEY (order_id) REFERENCES public.orders(order_id);


--
-- TOC entry 3316 (class 2606 OID 16543)
-- Name: orders payment_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT payment_fk FOREIGN KEY (payment_method) REFERENCES public.payment_methods(method_id);


--
-- TOC entry 3308 (class 2606 OID 16503)
-- Name: dishes restaurant_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dishes
    ADD CONSTRAINT restaurant_fk FOREIGN KEY (restaurant_id) REFERENCES public.restaurants(restaurant_id);


--
-- TOC entry 3320 (class 2606 OID 16563)
-- Name: reviews restaurant_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT restaurant_fk FOREIGN KEY (resturant_id) REFERENCES public.restaurants(restaurant_id);


--
-- TOC entry 3321 (class 2606 OID 16568)
-- Name: workers restaurant_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workers
    ADD CONSTRAINT restaurant_fk FOREIGN KEY (restaurant_id) REFERENCES public.restaurants(restaurant_id);


--
-- TOC entry 3317 (class 2606 OID 16548)
-- Name: orders status_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT status_fk FOREIGN KEY (status) REFERENCES public.statuses(status_id);


--
-- TOC entry 3304 (class 2606 OID 16483)
-- Name: accounts type_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT type_fk FOREIGN KEY (type) REFERENCES public.account_types(type_id);


--
-- TOC entry 3309 (class 2606 OID 16508)
-- Name: dishes type_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dishes
    ADD CONSTRAINT type_fk FOREIGN KEY (type_id) REFERENCES public.dish_types(type_id);


--
-- TOC entry 3322 (class 2606 OID 16573)
-- Name: workers worker_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workers
    ADD CONSTRAINT worker_fk FOREIGN KEY (worker) REFERENCES public.accounts(account_id);


-- Completed on 2023-12-07 13:24:26 CET

--
-- PostgreSQL database dump complete
--

