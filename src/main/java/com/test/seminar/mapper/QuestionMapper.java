package com.test.seminar.mapper;

import com.test.seminar.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author zhenweiwang
 * @date 2018/11/29
 *
 */
@Mapper
@Component
public interface QuestionMapper {

    List<Question> getQuestionBySeminarControlId(@Param("seminarControlId")BigInteger seminarControlId);

    List<Question> getQuestionByTeamId(@Param("teamId")BigInteger teamId);

    List<Question> getQuestionByPresentationId(@Param("presentationId")BigInteger presentationId);

    void insertQuestion(@Param("question")Question question, @Param("seminarControlId")BigInteger seminarControlId, @Param("presentationId") BigInteger presentationId, @Param("studentId")BigInteger studentId, @Param("teamId")BigInteger teamId);

    void updateQuestion(@Param("question")Question question, @Param("seminarControlId")BigInteger seminarControlId, @Param("presentationId") BigInteger presentationId, @Param("studentId")BigInteger studentId, @Param("teamId")BigInteger teamId);

    void deleteQuestionByQuestionId(@Param("questionId")BigInteger questionId);
}