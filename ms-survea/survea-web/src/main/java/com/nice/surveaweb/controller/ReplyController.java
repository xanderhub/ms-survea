package com.nice.surveaweb.controller;

import com.nice.survea.reply.exceptions.InvalidAnswerException;
import com.nice.survea.reply.exceptions.SurveyNotExistException;
import com.nice.survea.reply.model.Reply;
import com.nice.survea.reply.usecase.ReplyUseCase;
import com.nice.surveaweb.adapters.DtoAdapter;
import com.nice.surveaweb.dto.ErrorDto;
import com.nice.surveaweb.dto.ReplyRequest;
import com.nice.surveaweb.dto.ReplyResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.nice.surveaweb.adapters.DtoAdapter.toReplyDto;

@RestController
@RequestMapping("/reply")
public class ReplyController {

    private final ReplyUseCase replyUseCase;

    public ReplyController(ReplyUseCase replyUseCase) {
        this.replyUseCase = replyUseCase;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ReplyRequest replyRequest) {
        Reply reply = DtoAdapter.toReply(replyRequest.getReplyDto());
        try {
            reply = replyUseCase.create(reply);
            return new ResponseEntity(new ReplyResponse(DtoAdapter.toReplyDto(reply)), HttpStatus.CREATED);
        } catch (SurveyNotExistException exception) {
            return new ResponseEntity(new ErrorDto("SurveyNotExists"), HttpStatus.BAD_REQUEST);
        } catch (InvalidAnswerException exception) {
            return new ResponseEntity(new ErrorDto("WrongAnswerError"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{surveyName}")
    public ResponseEntity getBySurveyName(@PathVariable(value = "surveyName") String surveyName) {
        try {
            Reply reply = replyUseCase.getBySurveyName(surveyName);
            ReplyResponse replyResponse = new ReplyResponse(toReplyDto(reply));
            return ResponseEntity.ok(replyResponse);
        } catch (SurveyNotExistException exception) {
            return new ResponseEntity(new ErrorDto("SurveyNotExists"), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity(new ErrorDto("InvalidSurveyName"), HttpStatus.BAD_REQUEST);
        }
    }
}
