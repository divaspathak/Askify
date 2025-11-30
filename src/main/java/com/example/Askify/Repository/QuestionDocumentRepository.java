package com.example.Askify.Repository;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.example.Askify.Model.QuestionElasticDocument;

public interface QuestionDocumentRepository extends ElasticsearchRepository<QuestionElasticDocument, String>{
    List<QuestionElasticDocument> findByTitleOrContentContaining(String title, String content, PageRequest pageRequest);
}
