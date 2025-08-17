-- Note that the webhook URL *must* be HTTPS. If running locally, ensure that the server accepts https connections OR optionally use a reverse proxy url instead.
-- This demo application does not accept https connections, so the below configuration will not work locally. However replacing with reverse proxy url (like ngrok) will work.
CREATE CHANGEFEED INTO 'webhook-https://localhost:8080/api/v1/outbox-events/forward?insecure_tls_skip_verify=true' AS SELECT * FROM order_outbox_events WHERE NOT event_op() = 'delete';
