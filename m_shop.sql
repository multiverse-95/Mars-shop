PGDMP                          x            m_shop    12.2    12.2 <    ^           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            _           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            `           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            a           1262    17328    m_shop    DATABASE     �   CREATE DATABASE m_shop WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';
    DROP DATABASE m_shop;
                postgres    false                        2615    17329    Sch_Shop    SCHEMA        CREATE SCHEMA "Sch_Shop";
    DROP SCHEMA "Sch_Shop";
                postgres    false            �            1255    17330 /   get_product_id(text, integer, integer, integer)    FUNCTION     �  CREATE FUNCTION public.get_product_id(name_prod text, price_prod integer, category_prod integer, image_prod integer) RETURNS integer
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
       public          postgres    false            �            1259    17331    Basket    TABLE     �   CREATE TABLE "Sch_Shop"."Basket" (
    id bigint NOT NULL,
    customer_id bigint NOT NULL,
    product_id bigint NOT NULL,
    number integer,
    price integer
);
     DROP TABLE "Sch_Shop"."Basket";
       Sch_Shop         heap    postgres    false    7            �            1259    17334    Basket_id_seq    SEQUENCE     �   ALTER TABLE "Sch_Shop"."Basket" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME "Sch_Shop"."Basket_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 999999999
    CACHE 1
);
            Sch_Shop          postgres    false    7    203            �            1259    17336 
   Categoryes    TABLE     p   CREATE TABLE "Sch_Shop"."Categoryes" (
    id integer NOT NULL,
    category_name text,
    image_id integer
);
 $   DROP TABLE "Sch_Shop"."Categoryes";
       Sch_Shop         heap    postgres    false    7            �            1259    17342    Categoryes_id_seq    SEQUENCE     �   ALTER TABLE "Sch_Shop"."Categoryes" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME "Sch_Shop"."Categoryes_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            Sch_Shop          postgres    false    7    205            �            1259    17344    Favorite    TABLE     q   CREATE TABLE "Sch_Shop"."Favorite" (
    id integer NOT NULL,
    customer_id integer,
    product_id integer
);
 "   DROP TABLE "Sch_Shop"."Favorite";
       Sch_Shop         heap    postgres    false    7            �            1259    17347    Favorite_id_seq    SEQUENCE     �   ALTER TABLE "Sch_Shop"."Favorite" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME "Sch_Shop"."Favorite_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            Sch_Shop          postgres    false    207    7            �            1259    17349    Features    TABLE     �   CREATE TABLE "Sch_Shop"."Features" (
    id integer NOT NULL,
    product_id integer,
    feature_name text,
    feature_value text
);
 "   DROP TABLE "Sch_Shop"."Features";
       Sch_Shop         heap    postgres    false    7            �            1259    17355    Features_id_seq    SEQUENCE     �   ALTER TABLE "Sch_Shop"."Features" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME "Sch_Shop"."Features_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            Sch_Shop          postgres    false    7    209            �            1259    17357    Images    TABLE     M   CREATE TABLE "Sch_Shop"."Images" (
    id integer NOT NULL,
    path text
);
     DROP TABLE "Sch_Shop"."Images";
       Sch_Shop         heap    postgres    false    7            �            1259    17363    Images_id_seq    SEQUENCE     �   ALTER TABLE "Sch_Shop"."Images" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME "Sch_Shop"."Images_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            Sch_Shop          postgres    false    7    211            �            1259    17365    Order    TABLE     �   CREATE TABLE "Sch_Shop"."Order" (
    id bigint NOT NULL,
    product_id bigint NOT NULL,
    customer_id bigint NOT NULL,
    status integer,
    price bigint,
    number integer
);
    DROP TABLE "Sch_Shop"."Order";
       Sch_Shop         heap    postgres    false    7            �            1259    17368    Order_id_seq    SEQUENCE     �   ALTER TABLE "Sch_Shop"."Order" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME "Sch_Shop"."Order_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            Sch_Shop          postgres    false    7    213            �            1259    17370    products_idauto_seq    SEQUENCE     |   CREATE SEQUENCE public.products_idauto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.products_idauto_seq;
       public          postgres    false            �            1259    17372    Products    TABLE     �   CREATE TABLE "Sch_Shop"."Products" (
    id integer DEFAULT nextval('public.products_idauto_seq'::regclass) NOT NULL,
    name text NOT NULL,
    price integer NOT NULL,
    category_id integer,
    image_id integer
);
 "   DROP TABLE "Sch_Shop"."Products";
       Sch_Shop         heap    postgres    false    215    7            �            1259    17379    User    TABLE     ]  CREATE TABLE "Sch_Shop"."User" (
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
       Sch_Shop         heap    postgres    false    7            �            1259    17385    User_id_seq    SEQUENCE     �   ALTER TABLE "Sch_Shop"."User" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME "Sch_Shop"."User_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            Sch_Shop          postgres    false    7    217            L          0    17331    Basket 
   TABLE DATA           R   COPY "Sch_Shop"."Basket" (id, customer_id, product_id, number, price) FROM stdin;
    Sch_Shop          postgres    false    203   pG       N          0    17336 
   Categoryes 
   TABLE DATA           G   COPY "Sch_Shop"."Categoryes" (id, category_name, image_id) FROM stdin;
    Sch_Shop          postgres    false    205   �G       P          0    17344    Favorite 
   TABLE DATA           E   COPY "Sch_Shop"."Favorite" (id, customer_id, product_id) FROM stdin;
    Sch_Shop          postgres    false    207   �G       R          0    17349    Features 
   TABLE DATA           U   COPY "Sch_Shop"."Features" (id, product_id, feature_name, feature_value) FROM stdin;
    Sch_Shop          postgres    false    209   H       T          0    17357    Images 
   TABLE DATA           0   COPY "Sch_Shop"."Images" (id, path) FROM stdin;
    Sch_Shop          postgres    false    211   nJ       V          0    17365    Order 
   TABLE DATA           Y   COPY "Sch_Shop"."Order" (id, product_id, customer_id, status, price, number) FROM stdin;
    Sch_Shop          postgres    false    213   �J       Y          0    17372    Products 
   TABLE DATA           P   COPY "Sch_Shop"."Products" (id, name, price, category_id, image_id) FROM stdin;
    Sch_Shop          postgres    false    216   �J       Z          0    17379    User 
   TABLE DATA           �   COPY "Sch_Shop"."User" (id, password, login, role_number, phone_number, imail, avatar, first_name, second_name, third_name, country, city, street, mail_index) FROM stdin;
    Sch_Shop          postgres    false    217   �K       b           0    0    Basket_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('"Sch_Shop"."Basket_id_seq"', 232, true);
          Sch_Shop          postgres    false    204            c           0    0    Categoryes_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('"Sch_Shop"."Categoryes_id_seq"', 2, true);
          Sch_Shop          postgres    false    206            d           0    0    Favorite_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('"Sch_Shop"."Favorite_id_seq"', 12, true);
          Sch_Shop          postgres    false    208            e           0    0    Features_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('"Sch_Shop"."Features_id_seq"', 625, true);
          Sch_Shop          postgres    false    210            f           0    0    Images_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('"Sch_Shop"."Images_id_seq"', 6, true);
          Sch_Shop          postgres    false    212            g           0    0    Order_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('"Sch_Shop"."Order_id_seq"', 19, true);
          Sch_Shop          postgres    false    214            h           0    0    User_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('"Sch_Shop"."User_id_seq"', 5, true);
          Sch_Shop          postgres    false    218            i           0    0    products_idauto_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.products_idauto_seq', 90, true);
          public          postgres    false    215            �
           2606    17388    Basket Basket_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY "Sch_Shop"."Basket"
    ADD CONSTRAINT "Basket_pkey" PRIMARY KEY (id);
 D   ALTER TABLE ONLY "Sch_Shop"."Basket" DROP CONSTRAINT "Basket_pkey";
       Sch_Shop            postgres    false    203            �
           2606    17390    Categoryes Categoryes_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY "Sch_Shop"."Categoryes"
    ADD CONSTRAINT "Categoryes_pkey" PRIMARY KEY (id);
 L   ALTER TABLE ONLY "Sch_Shop"."Categoryes" DROP CONSTRAINT "Categoryes_pkey";
       Sch_Shop            postgres    false    205            �
           2606    17392    Features Features_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY "Sch_Shop"."Features"
    ADD CONSTRAINT "Features_pkey" PRIMARY KEY (id);
 H   ALTER TABLE ONLY "Sch_Shop"."Features" DROP CONSTRAINT "Features_pkey";
       Sch_Shop            postgres    false    209            �
           2606    17394    Images Images_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY "Sch_Shop"."Images"
    ADD CONSTRAINT "Images_pkey" PRIMARY KEY (id);
 D   ALTER TABLE ONLY "Sch_Shop"."Images" DROP CONSTRAINT "Images_pkey";
       Sch_Shop            postgres    false    211            �
           2606    17396    Order Order_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY "Sch_Shop"."Order"
    ADD CONSTRAINT "Order_pkey" PRIMARY KEY (id);
 B   ALTER TABLE ONLY "Sch_Shop"."Order" DROP CONSTRAINT "Order_pkey";
       Sch_Shop            postgres    false    213            �
           2606    17398    Products Products_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY "Sch_Shop"."Products"
    ADD CONSTRAINT "Products_pkey" PRIMARY KEY (id);
 H   ALTER TABLE ONLY "Sch_Shop"."Products" DROP CONSTRAINT "Products_pkey";
       Sch_Shop            postgres    false    216            �
           2606    17400    User User_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY "Sch_Shop"."User"
    ADD CONSTRAINT "User_pkey" PRIMARY KEY (id);
 @   ALTER TABLE ONLY "Sch_Shop"."User" DROP CONSTRAINT "User_pkey";
       Sch_Shop            postgres    false    217            �
           1259    17401    fk_fav_user    INDEX     M   CREATE INDEX fk_fav_user ON "Sch_Shop"."Favorite" USING btree (customer_id);
 #   DROP INDEX "Sch_Shop".fk_fav_user;
       Sch_Shop            postgres    false    207            �
           1259    17402    fk_prod_user    INDEX     M   CREATE INDEX fk_prod_user ON "Sch_Shop"."Favorite" USING btree (product_id);
 $   DROP INDEX "Sch_Shop".fk_prod_user;
       Sch_Shop            postgres    false    207            �
           1259    17403    fki_basket_user_id    INDEX     R   CREATE INDEX fki_basket_user_id ON "Sch_Shop"."Basket" USING btree (customer_id);
 *   DROP INDEX "Sch_Shop".fki_basket_user_id;
       Sch_Shop            postgres    false    203            �
           1259    17404    fki_cat_prod    INDEX     N   CREATE INDEX fki_cat_prod ON "Sch_Shop"."Products" USING btree (category_id);
 $   DROP INDEX "Sch_Shop".fki_cat_prod;
       Sch_Shop            postgres    false    216            �
           1259    17405    fki_feature_prod    INDEX     Q   CREATE INDEX fki_feature_prod ON "Sch_Shop"."Features" USING btree (product_id);
 (   DROP INDEX "Sch_Shop".fki_feature_prod;
       Sch_Shop            postgres    false    209            �
           1259    17406    fki_prod_img    INDEX     K   CREATE INDEX fki_prod_img ON "Sch_Shop"."Products" USING btree (image_id);
 $   DROP INDEX "Sch_Shop".fki_prod_img;
       Sch_Shop            postgres    false    216            �
           1259    17407    fki_ш    INDEX     I   CREATE INDEX "fki_ш" ON "Sch_Shop"."Categoryes" USING btree (image_id);
     DROP INDEX "Sch_Shop"."fki_ш";
       Sch_Shop            postgres    false    205            �
           2606    17408 #   Categoryes Categoryes_image_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY "Sch_Shop"."Categoryes"
    ADD CONSTRAINT "Categoryes_image_id_fkey" FOREIGN KEY (image_id) REFERENCES "Sch_Shop"."Images"(id) NOT VALID;
 U   ALTER TABLE ONLY "Sch_Shop"."Categoryes" DROP CONSTRAINT "Categoryes_image_id_fkey";
       Sch_Shop          postgres    false    211    205    2749            �
           2606    17413 "   Favorite Favorite_customer_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY "Sch_Shop"."Favorite"
    ADD CONSTRAINT "Favorite_customer_id_fkey" FOREIGN KEY (customer_id) REFERENCES "Sch_Shop"."User"(id) NOT VALID;
 T   ALTER TABLE ONLY "Sch_Shop"."Favorite" DROP CONSTRAINT "Favorite_customer_id_fkey";
       Sch_Shop          postgres    false    2757    207    217            �
           2606    17418 !   Favorite Favorite_product_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY "Sch_Shop"."Favorite"
    ADD CONSTRAINT "Favorite_product_id_fkey" FOREIGN KEY (product_id) REFERENCES "Sch_Shop"."Products"(id) NOT VALID;
 S   ALTER TABLE ONLY "Sch_Shop"."Favorite" DROP CONSTRAINT "Favorite_product_id_fkey";
       Sch_Shop          postgres    false    207    2753    216            �
           2606    17423 !   Features Features_proudct_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY "Sch_Shop"."Features"
    ADD CONSTRAINT "Features_proudct_id_fkey" FOREIGN KEY (product_id) REFERENCES "Sch_Shop"."Products"(id) NOT VALID;
 S   ALTER TABLE ONLY "Sch_Shop"."Features" DROP CONSTRAINT "Features_proudct_id_fkey";
       Sch_Shop          postgres    false    209    2753    216            �
           2606    17428    Order Order_customer_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY "Sch_Shop"."Order"
    ADD CONSTRAINT "Order_customer_id_fkey" FOREIGN KEY (customer_id) REFERENCES "Sch_Shop"."User"(id) NOT VALID;
 N   ALTER TABLE ONLY "Sch_Shop"."Order" DROP CONSTRAINT "Order_customer_id_fkey";
       Sch_Shop          postgres    false    2757    213    217            �
           2606    17433    Order Order_product_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY "Sch_Shop"."Order"
    ADD CONSTRAINT "Order_product_id_fkey" FOREIGN KEY (product_id) REFERENCES "Sch_Shop"."Products"(id) NOT VALID;
 M   ALTER TABLE ONLY "Sch_Shop"."Order" DROP CONSTRAINT "Order_product_id_fkey";
       Sch_Shop          postgres    false    213    216    2753            �
           2606    17438 "   Products Products_category_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY "Sch_Shop"."Products"
    ADD CONSTRAINT "Products_category_id_fkey" FOREIGN KEY (category_id) REFERENCES "Sch_Shop"."Categoryes"(id) NOT VALID;
 T   ALTER TABLE ONLY "Sch_Shop"."Products" DROP CONSTRAINT "Products_category_id_fkey";
       Sch_Shop          postgres    false    205    2741    216            �
           2606    17443    Products Products_image_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY "Sch_Shop"."Products"
    ADD CONSTRAINT "Products_image_id_fkey" FOREIGN KEY (image_id) REFERENCES "Sch_Shop"."Images"(id) NOT VALID;
 Q   ALTER TABLE ONLY "Sch_Shop"."Products" DROP CONSTRAINT "Products_image_id_fkey";
       Sch_Shop          postgres    false    211    2749    216            L      x�32��4��4�447������� $
      N   9   x�3�0���;.6]��4�2⼰��>���[.츰�bυ�@zׅ�F\1z\\\ �"      P      x���4����\����@N� '�      R   K  x��S�nA��������=ߵ(T!��6��Di;�Y
T(PP �HN��s�ٿ������A��E�ۙy;of�f�rJ.d�2�=��D��e%�����~̃�42��̕�(KҀ���P����! ��kt��)��L��O^�:I�ֶc�\�����5��oP����y�Ё2F�\�r���\����Z��b:��U<l�kY�����P����T��Կ	�����,��d}��5^�Z����;��,�OH%?0g�J���#�M��T7Sr�<���2Z#-Y4��A�Qu�"y@~��#��2[�>PsU7���>f~HF�9� �$e�ު��}{3�7<�V��7c;�s:#o��6��h./sUn��,6����'PO!������\nc1�r��F<X��(Sh:�˂�།�O�_��8��͘&�3��e(?����1���\(��h���햰I�./��d�}s��yr����u��Es���h_�����{�P<G�ݧ���ѳ�����������g�&�κK�CEku���ц8���l�i7�d��%�N��0i�Xc)a��G)SZz>4<�i�$���L�      T   B   x�3�,��,I�+�K�2�,JM+�LO-J,�/C���
ҹL #0��85"e�A�b���� 6�      V      x������ � �      Y   �   x���1
�@E��S�	d6�%��h�&�X���������e ��Q���F.!m�b�a�y�����/_Otx�+޾��X�3Γbي�#��U�P�����	���ug�h܍����Uh8)ys8�lE�,ԤE<C�Q�O�~Fmq�Ȏ�HO8�zA�ΧҥY����q��X�a��2F�ϔR?�Y�      Z   �   x��O�
�P]������7܄��$��6n�e�>!"��7��Q�U� �;g��s���βwB`�����_cx3#�.a�֦��B	�3�
9+�L��R�=�����N���#��x���`�O�]�K L���<K�$(d��X��2C{?P�VQ	#���#[ON���I��56��<|�~��!�eW�x     