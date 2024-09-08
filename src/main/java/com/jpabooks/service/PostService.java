package com.jpabooks.service;

import com.jpabooks.entity.PostDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PostService {
    private static String BASE_POST_URL ="https://jsonplaceholder.typicode.com/posts";
    public PostDTO getPostById(Long id)  {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PostDTO> post= restTemplate.getForEntity(BASE_POST_URL+"/"+id, PostDTO.class);
        return post.getBody();
    }

    public List<PostDTO> getAllPosts()  {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List> posts= restTemplate.getForEntity(BASE_POST_URL, List.class);
        return posts.getBody();
    }
    public PostDTO addPost(PostDTO postDTO)  {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<PostDTO>requset = new HttpEntity<>(postDTO);
        ResponseEntity<PostDTO> posts= restTemplate.postForEntity(BASE_POST_URL,requset, PostDTO.class);
        return posts.getBody();
    }
    public void updatePost(PostDTO postDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<PostDTO> request = new HttpEntity<>(postDTO);
        restTemplate.put(BASE_POST_URL, request, PostDTO.class);

    }



    public void deletePost(Long id)  {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(BASE_POST_URL+"/"+id);

    }
}
