package com.jisutudy.repository;

import com.jisutudy.domain.Booking;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.jisutudy.domain.QBooking.booking;
import static com.jisutudy.domain.customer.QCust.cust;

@Repository
public class BookingQueryDslImpl implements BookingQueryDsl {

    private final JPAQueryFactory queryFactory;
    public BookingQueryDslImpl(EntityManager entityManager) {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Booking> findAll(BookingSearch bookingSearch) {

        BooleanBuilder builder = new BooleanBuilder();
        if (bookingSearch.getCustName() != null) {
            builder.and(booking.cust.name.contains(bookingSearch.getCustName()));
        }

        if (bookingSearch.getBookingStatus() != null) {
            builder.and(booking.status.eq(bookingSearch.getBookingStatus()));
        }

        if (bookingSearch.getItemId() != null) {
            builder.and(booking.item.id.eq(bookingSearch.getItemId()));
        }

        return queryFactory
                .selectFrom(booking)
                .join(booking.cust, cust).fetchJoin()
                .where(builder)
                .limit(100)
                .fetch();
    }
}
