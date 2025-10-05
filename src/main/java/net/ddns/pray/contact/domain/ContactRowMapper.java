package net.ddns.pray.contact.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ContactRowMapper implements RowMapper<Contact> {

	@Override
	public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {

		return new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("date_of_birth"),
				rs.getString("phone_no"), rs.getString("email"));
	}
}
