create table if not exists order_outbox_events (
    id uuid primary key,
    created_at timestamptz not null,
    event_type text not null,
    event_data jsonb not null
);
