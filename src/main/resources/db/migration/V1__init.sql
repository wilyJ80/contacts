CREATE TABLE contact (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	name TEXT,
	date_of_birth TEXT,
	phone_no TEXT UNIQUE,
	email TEXT,
	CONSTRAINT must_have_contact_info
	CHECK (phone_no IS NOT NULL OR email IS NOT NULL)
) STRICT;
