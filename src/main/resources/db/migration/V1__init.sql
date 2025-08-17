CREATE TABLE IF NOT EXISTS orders (
    id uuid PRIMARY KEY,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    message TEXT NOT NULL
);
