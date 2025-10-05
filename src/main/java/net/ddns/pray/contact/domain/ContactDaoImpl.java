package net.ddns.pray.contact.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ContactDaoImpl implements ContactDao {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public ContactDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public List<Contact> findPage(int pageNumber, int pageSize) {

		int offset = (pageNumber - 1) * pageSize;
		String sql = """
				SELECT id, name, date_of_birth, phone_no, email
				FROM contact
				ORDER BY id DESC
				LIMIT :pageSize
				OFFSET :offset
				""";

		var params = new MapSqlParameterSource().addValue("pageSize", pageSize).addValue("offset", offset);
		return namedParameterJdbcTemplate.query(sql, params, new ContactRowMapper());
	}

	@Override
	public int countTotal() {

		String sql = """
				SELECT COUNT(id)
				FROM contact
				""";

		return namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(), Integer.class);
	}

	@Override
	public int countTotalByPhoneNo(String phoneNo) {

		String sql = """
				SELECT COUNT(id)
				FROM contact
				WHERE phone_no LIKE :phoneNo
				""";

		var params = new MapSqlParameterSource().addValue("phoneNo", phoneNo + "%");

		return namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
	}

	@Override
	public List<Contact> selectContactByPhoneNo(String phoneNo, int pageNumber, int pageSize) {

		int offset = (pageNumber - 1) * pageSize;
		String sql = """
				SELECT id, name, date_of_birth, phone_no, email
				FROM contact
				WHERE phone_no LIKE :phoneNo
				ORDER BY id DESC
				LIMIT :pageSize
				OFFSET :offset
				""";

		var params = new MapSqlParameterSource().addValue("phoneNo", phoneNo + "%")
				.addValue("pageSize", pageSize).addValue("offset", offset);

		return namedParameterJdbcTemplate.query(sql, params, new ContactRowMapper());
	}

	@Override
	public int addContact(Contact contact) {

		String sql = """
						INSERT INTO contact (name, date_of_birth, phone_no, email)
						VALUES (
						:name, :date_of_birth, :phone_no, :email
				)
						""";

		var params = new MapSqlParameterSource().addValue("name", contact.getName())
				.addValue("date_of_birth", contact.getDateOfBirth()).addValue("phone_no", contact.getPhoneNo())
				.addValue("email", contact.getEmail());

		return namedParameterJdbcTemplate.update(sql, params);
	}

	@Override
	public int deleteContact(int id) {

		String sql = """
				DELETE FROM contact
				WHERE id = :id
				""";

		var params = new MapSqlParameterSource().addValue("id", id);

		return namedParameterJdbcTemplate.update(sql, params);
	}

	@Override
	public Optional<Contact> selectContactById(int id) {

		String sql = """
				SELECT id, name, date_of_birth, phone_no, email
				FROM contact
				WHERE id = :id
				""";

		var params = new MapSqlParameterSource().addValue("id", id);

		return namedParameterJdbcTemplate.query(sql, params, new ContactRowMapper()).stream().findFirst();
	}

	@Override
	public int deleteContactByPrefix(String prefix) {

		final String sql = """
				DELETE FROM contact
				WHERE name LIKE :prefix
				""";

		var params = new MapSqlParameterSource().addValue("prefix", prefix + "%");

		return namedParameterJdbcTemplate.update(sql, params);
	}
}
