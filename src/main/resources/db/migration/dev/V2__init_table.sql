CREATE TABLE business_object (bo_id VARCHAR(255) NOT NULL, last_modified_date TIMESTAMP, created_date TIMESTAMP, creator_uid VARCHAR(255), description VARCHAR(255), bo_name VARCHAR(255) NOT NULL, last_modified_uid VARCHAR(255), PRIMARY KEY (bo_id), UNIQUE (bo_name));
CREATE TABLE business_object_field (field_id VARCHAR(255) NOT NULL, tenant_id VARCHAR(36), last_modified_date TIMESTAMP NOT NULL, created_date TIMESTAMP NOT NULL, creator_uid VARCHAR(255), is_mandatory BOOLEAN, is_visible BOOLEAN, is_cust_field BOOLEAN, is_multi_input BOOLEAN, is_value_set BOOLEAN, field_display_id VARCHAR(255) NOT NULL, field_type VARCHAR(255), last_modified_uid VARCHAR(255), bo_id VARCHAR(255), PRIMARY KEY (field_id));
CREATE TABLE business_object_field_text (field_text_id VARCHAR(255) NOT NULL, tenant_id VARCHAR(36), field_description VARCHAR(255), field_name VARCHAR(255), lang_id VARCHAR(255), field_id VARCHAR(255), PRIMARY KEY (field_text_id));
CREATE TABLE business_object_field_option (field_option_id VARCHAR(255) NOT NULL, tenant_id VARCHAR(36), field_id VARCHAR(255) NOT NULL, value VARCHAR(255) NOT NULL, PRIMARY KEY (field_option_id));
CREATE TABLE business_object_field_option_text (field_option_text_id VARCHAR(255) NOT NULL, tenant_id VARCHAR(36), lang_id VARCHAR(255), description VARCHAR(255), field_option_id VARCHAR(255), PRIMARY KEY (field_option_text_id));

ALTER TABLE business_object_field ADD CONSTRAINT UNQ_business_object_field_0 UNIQUE (tenant_id, field_display_id, bo_id);
ALTER TABLE business_object_field_text ADD CONSTRAINT UNQ_business_object_field_text_0 UNIQUE (field_id, lang_id);
ALTER TABLE business_object_field_option ADD CONSTRAINT UNQ_business_object_field_option_0 UNIQUE (field_id, value);
ALTER TABLE business_object_field_option_text ADD CONSTRAINT UNQ_business_object_field_option_text_0 UNIQUE (field_option_id, lang_id);

ALTER TABLE business_object_field ADD CONSTRAINT FK_business_object_field_bo_id FOREIGN KEY (bo_id) REFERENCES business_object (bo_id);
ALTER TABLE business_object_field_text ADD CONSTRAINT FK_business_object_field_text_field_id FOREIGN KEY (field_id) REFERENCES business_object_field (field_id);
ALTER TABLE business_object_field_option ADD CONSTRAINT FK_business_object_field_option_field_id FOREIGN KEY (field_id) REFERENCES business_object_field (field_id);
ALTER TABLE business_object_field_option_text ADD CONSTRAINT FK_business_object_field_option_text_field_option_id FOREIGN KEY (field_option_id) REFERENCES business_object_field_option (field_option_id);