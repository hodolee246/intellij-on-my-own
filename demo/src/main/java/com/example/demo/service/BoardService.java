package com.example.demo.service;

import com.example.demo.model.Board;
import com.example.demo.repository.BoardRepository;
import com.example.demo.specification.BoardSpecification;
import com.google.gson.Gson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Page<Board> boardLike(String category, String keyword, Pageable pageable) {
        Specification<Board> specification = Specification.where(BoardSpecification.boardLike(category, keyword));
        return boardRepository.findAll(specification, pageable);
    }

    public void saveBoard(Board board) {
        boardRepository.save(board);
    }

    public Optional<Board> getBoard(Long boardId) {
        return boardRepository.findById(boardId);
    }

    @Transactional
    public void updateBoard(Map<String, Object> bodyMap) {
        Gson gson = new Gson();
        Board board = gson.fromJson(bodyMap.toString(), Board.class);
        boardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(Board board) {
        boardRepository.delete(board);
    }

}
