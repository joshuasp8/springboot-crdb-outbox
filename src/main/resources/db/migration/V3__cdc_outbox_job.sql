SET CLUSTER SETTING kv.rangefeed.enabled = true;
CREATE CHANGEFEED INTO 'kafka://kafka:29092?topic_name=order-outbox-events' AS SELECT * FROM order_outbox_events WHERE NOT event_op() = 'delete';
