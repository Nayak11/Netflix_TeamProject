package com.moviecentral.mc.repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Pair;

import com.moviecentral.mc.entity.Payment;
import com.moviecentral.mc.entity.PlayHistory;
import com.moviecentral.mc.models.StatsUsers;

public interface PlayHistoryRepository extends JpaRepository<PlayHistory, Integer> {

	@Query( "SELECT e FROM PlayHistory e WHERE e.date >=?1 AND e.date <=?2 group by e.userid") 
	List<PlayHistory> findUseridDateBetween(Timestamp s,Timestamp s1);

	List<PlayHistory> findByUserid(Integer userid);
	
	List<PlayHistory> findByUseridAndMovieid(Integer userid, Integer movieid);

	List<PlayHistory> findByMovieid(Integer id);
	
	@Query( "select new com.moviecentral.mc.models.StatsUsers(p, count(p.userid)) from PlayHistory p where p.date >= :date group by p.userid order by 2 desc")
	List<StatsUsers> findStatsUsers(@Param("date")Timestamp date, @Param("pageable")Pageable pageable);
	
	@Query( "select new com.moviecentral.mc.models.StatsUsers(p, count(p.movieid)) from PlayHistory p where p.date >= :date group by p.movieid order by 2 desc")
	List<StatsUsers> findStatsMovies(@Param("date")Timestamp date, @Param("pageable")Pageable pageable);
	
	@Query("select p from PlayHistory p where p.date >= :date and p.userid = :userid and p.movieid = :movieid")
	List<PlayHistory> findIfPaid(@Param("date")Timestamp date, @Param("userid")Integer userid, @Param("movieid")Integer movieid);
}
