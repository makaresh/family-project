create table products_basket
(
    product_id   uuid unique      not null,
    product_name varchar          not null,
    quantity     double precision not null,
    comment      varchar
);

create table saved_products
(
    product_id   uuid unique not null,
    product_name varchar     not null
);

create table bought_products
(
    product_id uuid             not null,
    price      double precision not null,
    month      int              not null
);