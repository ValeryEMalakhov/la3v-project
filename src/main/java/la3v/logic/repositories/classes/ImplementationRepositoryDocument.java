package la3v.logic.repositories.classes;

import la3v.logic.entities.EntityDocument;
import la3v.logic.mappers.MapperDocument;
import la3v.logic.repositories.interfaces.IRepositoryDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Valery E. Malakhov on 02.04.2017.
 */
@Repository
public class ImplementationRepositoryDocument implements IRepositoryDocument {

    private JdbcTemplate template;

    private static final String GET_ALL_DOCUMENT_LIST = "SELECT * FROM \"admin\".\"document\" WHERE doc_date_archiving IS NULL";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM \"admin\".\"document\" WHERE doc_id = ?";
    private static final String FIND_BY_DOC_TEMPLATE_ID_QUERY = "SELECT * FROM \"admin\".\"document\" WHERE doc_template_id = ?";
    private static final String FIND_ARCHIVED_DOCUMENT = "SELECT * FROM \"admin\".\"document\" WHERE doc_date_archiving IS NOT NULL";

    @Autowired
    public ImplementationRepositoryDocument(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public EntityDocument findById(Integer id) {
        return this.template.queryForObject(FIND_BY_ID_QUERY, new Object[]{id}, new MapperDocument());
    }

    @Override
    public List<EntityDocument> getAllDocumentList() {
        return this.template.query(GET_ALL_DOCUMENT_LIST, new Object[]{}, new MapperDocument());
    }

    @Override
    public List<EntityDocument> getAllArchivedDocumentList(){
        return this.template.query(FIND_ARCHIVED_DOCUMENT, new Object[] {}, new MapperDocument());
    }

    @Override
    public EntityDocument findByDocTemplateId(Integer id) {
        return this.template.queryForObject(FIND_BY_DOC_TEMPLATE_ID_QUERY, new Object[]{id}, new MapperDocument());
    }
}
