PGDMP                         x            m_shop    12.2    12.2 -    F           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            G           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            H           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            I           1262    16878    m_shop    DATABASE     �   CREATE DATABASE m_shop WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';
    DROP DATABASE m_shop;
                postgres    false                        2615    16879    Sch_Shop    SCHEMA        CREATE SCHEMA "Sch_Shop";
    DROP SCHEMA "Sch_Shop";
                postgres    false            �            1255    16973 /   get_product_id(text, integer, integer, integer)    FUNCTION     �  CREATE FUNCTION public.get_product_id(name_prod text, price_prod integer, category_prod integer, image_prod integer) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
    product_id integer;
BEGIN
	INSERT INTO "Sch_Shop"."Products" (name,price,category_id,image_id)
    VALUES(name_prod,price_prod,category_prod,image_prod) returning id INTO STRICT product_id;
    RETURN product_id;
END; $$;
 t   DROP FUNCTION public.get_product_id(name_prod text, price_prod integer, category_prod integer, image_prod integer);
       public          postgres    false            �            1259    16880    Basket    TABLE     �   CREATE TABLE "Sch_Shop"."Basket" (
    id bigint NOT NULL,
    customer_id bigint NOT NULL,
    product_id bigint NOT NULL,
    number integer,
    price integer
);
     DROP TABLE "Sch_Shop"."Basket";
       Sch_Shop         heap    postgres    false    6            �            1259    16883    Basket_id_seq    SEQUENCE     �   ALTER TABLE "Sch_Shop"."Basket" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME "Sch_Shop"."Basket_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 999999999
    CACHE 1
);
            Sch_Shop          postgres    false    203    6            �            1259    16885 
   Categoryes    TABLE     p   CREATE TABLE "Sch_Shop"."Categoryes" (
    id integer NOT NULL,
    category_name text,
    image_id integer
);
 $   DROP TABLE "Sch_Shop"."Categoryes";
       Sch_Shop         heap    postgres    false    6            �            1259    16891    Categoryes_id_seq    SEQUENCE     �   ALTER TABLE "Sch_Shop"."Categoryes" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME "Sch_Shop"."Categoryes_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            Sch_Shop          postgres    false    6    205            �            1259    16893    Features    TABLE     �   CREATE TABLE "Sch_Shop"."Features" (
    id integer NOT NULL,
    product_id integer,
    feature_name text,
    feature_value text
);
 "   DROP TABLE "Sch_Shop"."Features";
       Sch_Shop         heap    postgres    false    6            �            1259    16899    Features_id_seq    SEQUENCE     �   ALTER TABLE "Sch_Shop"."Features" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME "Sch_Shop"."Features_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            Sch_Shop          postgres    false    6    207            �            1259    16901    Images    TABLE     M   CREATE TABLE "Sch_Shop"."Images" (
    id integer NOT NULL,
    path text
);
     DROP TABLE "Sch_Shop"."Images";
       Sch_Shop         heap    postgres    false    6            �            1259    16907    Images_id_seq    SEQUENCE     �   ALTER TABLE "Sch_Shop"."Images" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME "Sch_Shop"."Images_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            Sch_Shop          postgres    false    6    209            �            1259    16909    products_idauto_seq    SEQUENCE     |   CREATE SEQUENCE public.products_idauto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.products_idauto_seq;
       public          postgres    false            �            1259    16911    Products    TABLE     �   CREATE TABLE "Sch_Shop"."Products" (
    id integer DEFAULT nextval('public.products_idauto_seq'::regclass) NOT NULL,
    name text NOT NULL,
    price integer NOT NULL,
    category_id integer,
    image_id integer
);
 "   DROP TABLE "Sch_Shop"."Products";
       Sch_Shop         heap    postgres    false    211    6            �            1259    16974    User    TABLE     ]  CREATE TABLE "Sch_Shop"."User" (
    id bigint NOT NULL,
    password text NOT NULL,
    login text NOT NULL,
    role_number bigint NOT NULL,
    phone_number bigint,
    imail text NOT NULL,
    avatar text,
    first_name text,
    second_name text,
    third_name text,
    country text,
    city text,
    street text,
    mail_index bigint
);
    DROP TABLE "Sch_Shop"."User";
       Sch_Shop         heap    postgres    false    6            �            1259    16977    User_id_seq    SEQUENCE     �   ALTER TABLE "Sch_Shop"."User" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME "Sch_Shop"."User_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            Sch_Shop          postgres    false    6    213            8          0    16880    Basket 
   TABLE DATA           R   COPY "Sch_Shop"."Basket" (id, customer_id, product_id, number, price) FROM stdin;
    Sch_Shop          postgres    false    203   �4       :          0    16885 
   Categoryes 
   TABLE DATA           G   COPY "Sch_Shop"."Categoryes" (id, category_name, image_id) FROM stdin;
    Sch_Shop          postgres    false    205   >5       <          0    16893    Features 
   TABLE DATA           U   COPY "Sch_Shop"."Features" (id, product_id, feature_name, feature_value) FROM stdin;
    Sch_Shop          postgres    false    207   �5       >          0    16901    Images 
   TABLE DATA           0   COPY "Sch_Shop"."Images" (id, path) FROM stdin;
    Sch_Shop          postgres    false    209   �7       A          0    16911    Products 
   TABLE DATA           P   COPY "Sch_Shop"."Products" (id, name, price, category_id, image_id) FROM stdin;
    Sch_Shop          postgres    false    212   48       B          0    16974    User 
   TABLE DATA           �   COPY "Sch_Shop"."User" (id, password, login, role_number, phone_number, imail, avatar, first_name, second_name, third_name, country, city, street, mail_index) FROM stdin;
    Sch_Shop          postgres    false    213   �8       J           0    0    Basket_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('"Sch_Shop"."Basket_id_seq"', 207, true);
          Sch_Shop          postgres    false    204            K           0    0    Categoryes_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('"Sch_Shop"."Categoryes_id_seq"', 2, true);
          Sch_Shop          postgres    false    206            L           0    0    Features_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('"Sch_Shop"."Features_id_seq"', 619, true);
          Sch_Shop          postgres    false    208            M           0    0    Images_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('"Sch_Shop"."Images_id_seq"', 6, true);
          Sch_Shop          postgres    false    210            N           0    0    User_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('"Sch_Shop"."User_id_seq"', 4, true);
          Sch_Shop          postgres    false    214            O           0    0    products_idauto_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.products_idauto_seq', 86, true);
          public          postgres    false    211            �
           2606    16927    Basket Basket_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY "Sch_Shop"."Basket"
    ADD CONSTRAINT "Basket_pkey" PRIMARY KEY (id);
 D   ALTER TABLE ONLY "Sch_Shop"."Basket" DROP CONSTRAINT "Basket_pkey";
       Sch_Shop            postgres    false    203            �
           2606    16929    Categoryes Categoryes_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY "Sch_Shop"."Categoryes"
    ADD CONSTRAINT "Categoryes_pkey" PRIMARY KEY (id);
 L   ALTER TABLE ONLY "Sch_Shop"."Categoryes" DROP CONSTRAINT "Categoryes_pkey";
       Sch_Shop            postgres    false    205            �
           2606    16931    Features Features_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY "Sch_Shop"."Features"
    ADD CONSTRAINT "Features_pkey" PRIMARY KEY (id);
 H   ALTER TABLE ONLY "Sch_Shop"."Features" DROP CONSTRAINT "Features_pkey";
       Sch_Shop            postgres    false    207            �
           2606    16933    Images Images_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY "Sch_Shop"."Images"
    ADD CONSTRAINT "Images_pkey" PRIMARY KEY (id);
 D   ALTER TABLE ONLY "Sch_Shop"."Images" DROP CONSTRAINT "Images_pkey";
       Sch_Shop            postgres    false    209            �
           2606    16935    Products Products_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY "Sch_Shop"."Products"
    ADD CONSTRAINT "Products_pkey" PRIMARY KEY (id);
 H   ALTER TABLE ONLY "Sch_Shop"."Products" DROP CONSTRAINT "Products_pkey";
       Sch_Shop            postgres    false    212            �
           2606    16986    User User_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY "Sch_Shop"."User"
    ADD CONSTRAINT "User_pkey" PRIMARY KEY (id);
 @   ALTER TABLE ONLY "Sch_Shop"."User" DROP CONSTRAINT "User_pkey";
       Sch_Shop            postgres    false    213            �
           1259    16938    fki_basket_user_id    INDEX     R   CREATE INDEX fki_basket_user_id ON "Sch_Shop"."Basket" USING btree (customer_id);
 *   DROP INDEX "Sch_Shop".fki_basket_user_id;
       Sch_Shop            postgres    false    203            �
           1259    16939    fki_cat_prod    INDEX     N   CREATE INDEX fki_cat_prod ON "Sch_Shop"."Products" USING btree (category_id);
 $   DROP INDEX "Sch_Shop".fki_cat_prod;
       Sch_Shop            postgres    false    212            �
           1259    16940    fki_feature_prod    INDEX     Q   CREATE INDEX fki_feature_prod ON "Sch_Shop"."Features" USING btree (product_id);
 (   DROP INDEX "Sch_Shop".fki_feature_prod;
       Sch_Shop            postgres    false    207            �
           1259    16941    fki_prod_img    INDEX     K   CREATE INDEX fki_prod_img ON "Sch_Shop"."Products" USING btree (image_id);
 $   DROP INDEX "Sch_Shop".fki_prod_img;
       Sch_Shop            postgres    false    212            �
           1259    16942    fki_ш    INDEX     I   CREATE INDEX "fki_ш" ON "Sch_Shop"."Categoryes" USING btree (image_id);
     DROP INDEX "Sch_Shop"."fki_ш";
       Sch_Shop            postgres    false    205            �
           2606    16948 #   Categoryes Categoryes_image_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY "Sch_Shop"."Categoryes"
    ADD CONSTRAINT "Categoryes_image_id_fkey" FOREIGN KEY (image_id) REFERENCES "Sch_Shop"."Images"(id) NOT VALID;
 U   ALTER TABLE ONLY "Sch_Shop"."Categoryes" DROP CONSTRAINT "Categoryes_image_id_fkey";
       Sch_Shop          postgres    false    209    2735    205            �
           2606    16953 !   Features Features_proudct_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY "Sch_Shop"."Features"
    ADD CONSTRAINT "Features_proudct_id_fkey" FOREIGN KEY (product_id) REFERENCES "Sch_Shop"."Products"(id) NOT VALID;
 S   ALTER TABLE ONLY "Sch_Shop"."Features" DROP CONSTRAINT "Features_proudct_id_fkey";
       Sch_Shop          postgres    false    2737    207    212            �
           2606    16958 "   Products Products_category_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY "Sch_Shop"."Products"
    ADD CONSTRAINT "Products_category_id_fkey" FOREIGN KEY (category_id) REFERENCES "Sch_Shop"."Categoryes"(id) NOT VALID;
 T   ALTER TABLE ONLY "Sch_Shop"."Products" DROP CONSTRAINT "Products_category_id_fkey";
       Sch_Shop          postgres    false    205    212    2729            �
           2606    16963    Products Products_image_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY "Sch_Shop"."Products"
    ADD CONSTRAINT "Products_image_id_fkey" FOREIGN KEY (image_id) REFERENCES "Sch_Shop"."Images"(id) NOT VALID;
 Q   ALTER TABLE ONLY "Sch_Shop"."Products" DROP CONSTRAINT "Products_image_id_fkey";
       Sch_Shop          postgres    false    212    2735    209            8   E   x�E̱�PC����d�9b~���5�N	I11`F���u~nz��C)wP���pvp��s��5&      :   9   x�3�0���;.6]��4�2⼰��>���[.츰�bυ�@zׅ�F\1z\\\ �"      <   K  x��S�nA��������=ߵ(T!��6��Di;�Y
T(PP �HN��s�ٿ������A��E�ۙy;of�f�rJ.d�2�=��D��e%�����~̃�42��̕�(KҀ���P����! ��kt��)��L��O^�:I�ֶc�\�����5��oP����y�Ё2F�\�r���\����Z��b:��U<l�kY�����P����T��Կ	�����,��d}��5^�Z����;��,�OH%?0g�J���#�M��T7Sr�<���2Z#-Y4��A�Qu�"y@~��#��2[�>PsU7���>f~HF�9� �$e�ު��}{3�7<�V��7c;�s:#o��6��h./sUn��,6����'PO!������\nc1�r��F<X��(Sh:�˂�།�O�_��8��͘&�3��e(?����1���\(��h���햰I�./��d�}s��yr����u��Es���h_�����{�P<G�ݧ���ѳ�����������g�&�κK�CEku���ц8���l�i7�d��%�N��0i�Xc)a��G)SZz>4<�i�$���L�      >   B   x�3�,��,I�+�K�2�,JM+�LO-J,�/C���
ҹL #0��85"e�A�b���� 6�      A   �   x���1
�@E��S�	d6�%��h�&�X���������e ��Q���F.!m�b�a�y�����/_Otx�+޾��X�3Γbي�#��U�P�����	���ug�h܍����Uh8)ys8�lE�,ԤE<C�Q�O�~Fmq�Ȏ�HO8�zA�ΧҥY����q��X�a��2F�ϔR?�Y�      B   �   x�3�LL���3426��8�8c� L0�WT
1P�!'XoqNbYv"�!���P��ya������^l�����@j�Ş�.�r9/,���b#Pz��~��.l����$ya'��	Ď���b� ��s���i�����b���� 4�m      