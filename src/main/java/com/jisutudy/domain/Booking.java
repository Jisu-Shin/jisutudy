package com.jisutudy.domain;

import com.jisutudy.domain.customer.Cust;
import com.jisutudy.domain.performance.Item;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Booking {

    @Id @GeneratedValue
    @Column(name="booking_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "cust_id")
    private Cust cust;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count; // 주문 수량

    private LocalDateTime bookDt; // 예매시간

    @Enumerated(EnumType.STRING)
    private BookingStatus status; //예매상태 [예매, 취소]

    //==연관관계 메서드==//
    public void setCust(Cust cust) {
        this.cust = cust;
        cust.getBookingList().add(this);
    }

    public void setItem(Item item){
        this.item = item;
        item.getBookingList().add(this);
    }

    //==생성 메서드==//
    public static Booking createBooking(Cust cust, Item item, int count) {
        Booking booking = new Booking();
        booking.setCust(cust);
        booking.setItem(item);

        booking.setCount(count);
        booking.setStatus(BookingStatus.BOOK);
        booking.setBookDt(LocalDateTime.now());

        item.removeStock(count);

        return booking;
    }

    //==비즈니스 로직==//
    /**
     * 주문 취소
     */
    public void cancel() {
        // TODO 취소 불가 시점 (공연 일주일 전이라던가...)
//        if (getStatus() == FAIL_CANCEL) {
//              throw new IllegalStateException("일주일 전 공연은 취소가 불가능합니다.");
//        }

        this.setStatus(BookingStatus.CANCEL);
        item.addStock(count);
    }

    //== 조회 로직==//
    /**
     * 전체 티켓 가격 조회
     */
    public int getTotalPrice() {
        int totalPrice = 0;
        totalPrice = count * item.getPrice();
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", cust=" + cust +
                ", item=" + item +
                ", count=" + count +
                ", bookDt=" + bookDt +
                ", status=" + status +
                '}';
    }
}
