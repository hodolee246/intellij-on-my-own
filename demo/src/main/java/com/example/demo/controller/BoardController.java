package com.example.demo.controller;

import com.example.demo.model.Board;
import com.example.demo.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@Slf4j
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board")
    public ResponseEntity<?> boardList(@PageableDefault(size = 3) Pageable pageable,
                                       @RequestParam(name = "category", defaultValue = "") String category,
                                       @RequestParam(name = "keyword", defaultValue = "") String keyword) {

        log.info("page : {} / category : {} / keyword : {}", pageable.toString(), category, keyword);
        HashMap<String, Object> resultMap = new HashMap<>();
        Page<Board> boardPage = boardService.boardLike(category, keyword, pageable);

        if (boardPage != null) {
            List<Board> boards = boardPage.getContent();
            resultMap.put("list", boards);
            resultMap.put("totalPages", boardPage.getTotalPages());
            resultMap.put("page", boardPage.getNumber());
        } else {
            resultMap.put("result", false);
            resultMap.put("reason", "There is no date in boardList");

            return badRequest().body(resultMap);
        }

        return ok().body(resultMap);
    }

    @GetMapping("/board/{boardId}")
    public ResponseEntity<?> getBoard(@PathVariable Long boardId) {

        log.info("BoardController getBoard");
        if (boardId == 0) {
            return badRequest().body(new JSONObject().appendField("result", false));
        }

        Optional<Board> board = boardService.getBoard(boardId);

        if (board.isPresent()) {
            return ok().body(new JSONObject().appendField("board", board));
        }

        return badRequest().body(new JSONObject().appendField("result", false));
    }

    @PostMapping("/board")
    public ResponseEntity<?> boardWrite(@RequestBody Board board) {

        log.info("BoardController boardWrite");
        if (board == null) {
            return badRequest().body(new JSONObject().appendField("result", "false"));
        }

        boardService.saveBoard(board);

        return ok().body(new JSONObject().appendField("result", "true"));
    }

    @PutMapping("/board")
    public ResponseEntity<?> updateBoard(@RequestBody Map<String, Object> bodyMap) {

        log.info("BoardController updateBoard");
        if (bodyMap.isEmpty()) {
            return badRequest().body(new JSONObject().appendField("result", false));
        }

        boardService.updateBoard(bodyMap);

        return ok().body(new JSONObject().appendField("result", "true"));
    }

    @DeleteMapping("/board/{boardId}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long boardId) {

        log.info("BoardController deleteBoard");
        if (boardId == 0) {
            return badRequest().body(new JSONObject().appendField("result", false));
        }

        Optional<Board> board = boardService.getBoard(boardId);

        if (board.isPresent()) {
            boardService.deleteBoard(board.get());
            return ok().body(new JSONObject().appendField("result", "true"));
        }

        return badRequest().body(new JSONObject().appendField("result", false));
    }
}
