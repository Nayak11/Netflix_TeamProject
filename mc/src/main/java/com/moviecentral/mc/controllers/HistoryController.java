package com.moviecentral.mc.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moviecentral.mc.entity.Movie;
import com.moviecentral.mc.entity.PlayHistory;
import com.moviecentral.mc.entity.User;
import com.moviecentral.mc.models.HistoryResponse;
import com.moviecentral.mc.models.LoginResponse;
import com.moviecentral.mc.models.SearchMovie;
import com.moviecentral.mc.models.SearchResponse;
import com.moviecentral.mc.models.UserInfo;
import com.moviecentral.mc.repository.MovieRepository;
import com.moviecentral.mc.repository.PlayHistoryRepository;
import com.moviecentral.mc.repository.UserRepository;
import com.moviecentral.mc.utils.Session;

@RestController
public class HistoryController {
	
	@Autowired
	private PlayHistoryRepository playHistoryRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@GetMapping(value = "/history")
	@CrossOrigin(origins = {"http://54.193.119.24:3000", "http://localhost:3000"})
	public HistoryResponse history(HttpSession session, @RequestParam("userid") Optional<Integer> userid, @RequestParam("movieid") Optional<Integer> movieid){
		HistoryResponse historyResponse = new HistoryResponse();
		if(userid.isPresent()){
			Integer id = userid.get();
			List<PlayHistory> playHistory = playHistoryRepository.findByUserid(id);
			
			List<Integer> movieids = new ArrayList<Integer>();
			for(PlayHistory p: playHistory){
				movieids.add(p.getMovieid());
			}
			
			List<Movie> movies = movieRepository.findByMovieidIn(movieids);
			
			List<SearchMovie> list = new ArrayList<SearchMovie>();
			
			for(Movie m : movies){
				System.out.println(m.getTitle());
				SearchMovie s = new SearchMovie();
				s.setMovieid(m.getMovieid());
				s.setMovie(m.getMovie());
				s.setTitle(m.getTitle());
				s.setYear(m.getYear());
				s.setStudio(m.getStudio());
				s.setSynopsis(m.getSynopsis());
				s.setImage(m.getImage());
				s.setMovie(m.getMovie());
				s.setActors(m.getActors());
				s.setDirector(m.getDirector());
				s.setCountry(m.getCountry());
				s.setRating(m.getRating());
				s.setAvailability(m.getAvailability());
				s.setPrice(m.getPrice());
				list.add(s);
			}
			historyResponse.setStatus("SUCCESS");
			historyResponse.setMovies(list);
		} else if(movieid.isPresent()){
			Integer id = movieid.get();
			List<PlayHistory> playHistory = playHistoryRepository.findByMovieid(id);
			
			List<Integer> userids = new ArrayList<Integer>();
			for(PlayHistory p: playHistory){
				userids.add(p.getUserid());
			}
			
			List<User> users = userRepository.findByUseridIn(userids);
			
			List<UserInfo> list = new ArrayList<UserInfo>();
			
			for(User u : users){
				UserInfo ui = new UserInfo();
				
				ui.setUserid(u.getUserid());
				ui.setUsername(u.getUsername());
				ui.setEmail(u.getEmail());
				ui.setType(u.getType());
				ui.setSubscription(u.getSubscription());
				ui.setActivated(u.getActivated());
				ui.setStartdate(u.getStartdate());
				ui.setEnddate(u.getEnddate());
				
				list.add(ui);
			}
			historyResponse.setStatus("SUCCESS");
			historyResponse.setUsers(list);
		} else {
			historyResponse.setStatus("FAILURE");
			historyResponse.setMessage("invalid query");
		}
		return historyResponse;
	}
	
}
