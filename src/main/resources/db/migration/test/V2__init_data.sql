INSERT INTO business_object (bo_id, last_modified_date, last_modified_uid, created_date, creator_uid, description, bo_name) VALUES
('4A54DBDA-6BC1-45EF-A92C-EBD9EADF4B33', '2018-01-16 15:59:18.466', 'gdpr_uid', '2018-01-16 15:59:18.466', 'gdpr_uid', 'ropa descritpion', 'ROPA');


INSERT INTO business_object_field (field_id, last_modified_date, last_modified_uid, created_date, creator_uid, is_mandatory, is_visible, is_cust_field, is_multi_input, is_value_set, field_display_id, field_type, bo_id, tenant_id) VALUES
('A7F21EBC-3F4E-4767-85B6-F6C1AE15F152', '2018-01-16 16:24:04.225', 'gdpr_uid', '2018-01-16 16:24:04.225', 'gdpr_uid', 'true', 'true', 'true', 'true', 'true', 'CDF1', 'STRING', '4A54DBDA-6BC1-45EF-A92C-EBD9EADF4B33', 'gdpr_tenant');

INSERT INTO business_object_field_text (field_text_id, field_description, field_name, lang_id, field_id, tenant_id) VALUES
('08E365EE-ADD4-476B-ACC1-5A2BE182AF15', 'custom field 1 for ropa', 'custom field', 'en', 'A7F21EBC-3F4E-4767-85B6-F6C1AE15F152', 'gdpr_tenant');

INSERT INTO business_object_field_option (field_option_id, field_id, value, tenant_id) VALUES
('08E365EE-ADD4-476B-ACC1-5A2LDS92LF20', 'A7F21EBC-3F4E-4767-85B6-F6C1AE15F152', 'value_1', 'gdpr_tenant');

INSERT INTO business_object_field_option_text (field_option_text_id, lang_id, description, field_option_id, tenant_id) VALUES
('08E365EE-ADD4-476B-ABB1-5A2LDS92LF20', 'en', 'value description 1', '08E365EE-ADD4-476B-ACC1-5A2LDS92LF20', 'gdpr_tenant');



INSERT INTO business_object_field (field_id, last_modified_date, last_modified_uid, created_date, creator_uid, is_mandatory, is_visible, is_cust_field, is_multi_input, is_value_set, field_display_id, field_type, bo_id, tenant_id) VALUES
('5E85650E-EDF2-492A-8177-37E3764F6D57', '2018-01-16 16:24:04.225', 'gdpr_uid', '2018-01-16 16:24:04.225', 'gdpr_uid', 'true', 'true', 'true', 'true', 'true', 'CDF2', 'STRING', '4A54DBDA-6BC1-45EF-A92C-EBD9EADF4B33', 'gdpr_tenant');

INSERT INTO business_object_field_text (field_text_id, field_description, field_name, lang_id, field_id, tenant_id) VALUES
('3998A580-170F-4379-AAF8-2D9D355B2B9E', 'custom field 2 for ropa', 'custom field 2', 'en', '5E85650E-EDF2-492A-8177-37E3764F6D57', 'gdpr_tenant');

INSERT INTO business_object_field_option (field_option_id, field_id, value, tenant_id) VALUES
('08E365EE-ADD4-476B-ACC1-5A2LDS92LF21', '5E85650E-EDF2-492A-8177-37E3764F6D57', 'value_1', 'gdpr_tenant');

INSERT INTO business_object_field_option_text (field_option_text_id, lang_id, description, field_option_id, tenant_id) VALUES
('08E365EE-ADD4-476B-ABB1-5A2LDS92LF21', 'en', 'value description 1', '08E365EE-ADD4-476B-ACC1-5A2LDS92LF21', 'gdpr_tenant');

INSERT INTO business_object_field_option (field_option_id, field_id, value, tenant_id) VALUES
('08E365EE-ADD4-476B-ACC1-5A2LDS92LF22', '5E85650E-EDF2-492A-8177-37E3764F6D57', 'value_2', 'gdpr_tenant');

INSERT INTO business_object_field_option_text (field_option_text_id, lang_id, description, field_option_id, tenant_id) VALUES
('08E365EE-ADD4-476B-ABB1-5A2LDS92LF22', 'en', 'value description 2', '08E365EE-ADD4-476B-ACC1-5A2LDS92LF22', 'gdpr_tenant');