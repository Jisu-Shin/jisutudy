package com.jisutudy.repository;

import com.jisutudy.domain.sms.Sms;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.jisutudy.domain.sms.QSms.sms;

@Repository
public class SmsQueryDslImpl implements SmsQueryDsl{

    private final JPAQueryFactory queryFactory;
    public SmsQueryDslImpl(EntityManager entityManager) {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Sms> findBySendDt(SmsSearch smsSearch) {
        BooleanBuilder builder = new BooleanBuilder();
        if (smsSearch.getStartDt() != null && smsSearch.getEndDt() != null ) {
            builder.and(sms.sendDt.between(smsSearch.getStartDt(), smsSearch.getEndDt()));
        }

        if (smsSearch.getCustId() != null) {
            builder.and(sms.cust.id.eq(smsSearch.getCustId()));
        }

        if (smsSearch.getSmsType() != null) {
            builder.and(sms.smsTemplate.smsType.eq(smsSearch.getSmsType()));
        }

        //TODO fetchjoin?
        return queryFactory
                .selectFrom(sms)
                .where(builder)
                .limit(100)
                .fetch();
    }
}
