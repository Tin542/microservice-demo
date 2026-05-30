package com.tinnt.finance_service.repository;

import com.tinnt.finance_service.common.util.CodeGenerator;
import com.tinnt.finance_service.dto.request.CreateIncomeRequestDto;
import com.tinnt.finance_service.entity.IncomeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class IncomeRepositoryImpl implements IncomeRepository {

    private static final String MONGODB_COLLECTION = "incomes";

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public IncomeEntity createIncome(CreateIncomeRequestDto request) {
        IncomeEntity entity = new IncomeEntity();

        entity.setIncomeCode(CodeGenerator.generate("INC"));
        entity.setNote(request.getNote());
        entity.setDate(request.getDate());
        entity.setAmount(request.getAmount());
        entity.setCategoryCode(request.getCategoryCode());

        return mongoTemplate.save(entity);
    }

    @Override
    public IncomeEntity getIncomeByCode(String incomeCode) {
        Query query = new Query();
        query.addCriteria(Criteria.where("incomeCode").is(incomeCode));

        IncomeEntity entity = mongoTemplate.findOne(query, IncomeEntity.class, MONGODB_COLLECTION);

        return entity;
    }
}
