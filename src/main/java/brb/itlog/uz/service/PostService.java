package brb.itlog.uz.service;

import brb.itlog.uz.model.dto.post.request.CreatePostRequestDTO;
import brb.itlog.uz.model.dto.post.request.PostDTO;
import brb.itlog.uz.model.pagination.MetaDTO;

public interface PostService {

    String createPost(CreatePostRequestDTO createPostRequestDTO);

    PostDTO copyPost(Long id);

    String updateTitle(Long postId, String newTitle);

    String updatePostStatusPublished(Long id, String status);

    String updatePostStatusScheduled(Long id, String status, String publishedAt);

    String deletePost(Long id);

    MetaDTO getAllPosts(int page, int pageSize);



}
