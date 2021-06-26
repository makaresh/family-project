create table products_basket
(
    product_id   uuid unique      not null,
    product_name varchar          not null,
    price        double precision,
    quantity     double precision not null,
    comment      varchar,
    necessity    int8,
    user_name    int8,
    season       int8
);

create table saved_products
(
    product_id   uuid unique not null,
    product_name varchar     not null,
    all_prices   double precision[] not null
);

create table bought_products
(
    product_id uuid             not null,
    price      double precision not null,
    month      int              not null
);