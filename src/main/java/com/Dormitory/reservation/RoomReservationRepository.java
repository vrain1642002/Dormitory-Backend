package com.Dormitory.reservation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Dormitory.room.Room;
import com.Dormitory.sesmester.Sesmester;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Integer>{
    Boolean existsByStudentId(Integer id);
    List<RoomReservation> findAllByOrderByBookingDateTimeAsc();
    @Modifying
    @Query("UPDATE RoomReservation r SET r.sesmester = :sesmester,r.status=0, r.room = :room, r.bookingDateTime =:bookingDateTime WHERE r.student.id = :studentId")
    void updateByStudentId(@Param("sesmester") Sesmester sesmester, @Param("room") Room room,@Param("bookingDateTime") LocalDateTime bookingDateTime, @Param("studentId") Integer studentId);
    @Modifying
    @Query("UPDATE RoomReservation r SET r.status = 1 where r.id = :id")
    void updateStatusToTrueById(@Param("id") Integer id);
    @Query("SELECT rr FROM RoomReservation rr " +
           "JOIN rr.student student " +
           "JOIN rr.sesmester sesmester " +
           "WHERE student.numberStudent = :numberStudent " +
           "AND sesmester.status = true")
    Optional<RoomReservation> findRoomReservationsByStudentNumberAndSesmesterStatusIsTrue(String numberStudent);
}
