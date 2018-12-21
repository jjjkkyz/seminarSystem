package com.test.seminar.dao.impl;

import com.test.seminar.dao.RoundDao;
import com.test.seminar.entity.Round;
import com.test.seminar.entity.RoundScore;
import com.test.seminar.entity.SeminarScore;
import com.test.seminar.exception.RepetitiveRecordException;
import com.test.seminar.exception.RoundNotFoundException;
import com.test.seminar.mapper.RoundMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @author zhenweiwang
 * @date 2018/11/29
 */
@Component
public class RoundDaoImpl implements RoundDao {
    @Autowired
    RoundMapper roundMapper;

    @Override
    public Round getRoundByRoundId(BigInteger roundId)throws RoundNotFoundException {
        Round round=roundMapper.getRoundByRoundId(roundId);
        if(round==null) {
            throw new RoundNotFoundException();
        }
        return round;
    }

    @Override
    public List<Round> getRoundByCourseId(BigInteger courseId) {
        return roundMapper.getRoundByCourseId(courseId);
    }

    @Override
    public int getRoundSerialBySeminarInfoId(BigInteger seminarInfoId){
        return roundMapper.getRoundSerialBySeminarInfoId(seminarInfoId);
    }


    @Override
    public void insertRound(Round round, BigInteger courseId)throws RepetitiveRecordException {
        roundMapper.insertRound(round,courseId);
    }

    @Override
    public void updateRound(Round round)throws RoundNotFoundException {
        if(roundMapper.getRoundByRoundId(round.getId())==null) {
            throw new RoundNotFoundException();
        }
        roundMapper.updateRound(round);
    }

    @Override
    public void deleteRoundByRoundId(BigInteger roundId)throws RoundNotFoundException {
        if(roundMapper.getRoundByRoundId(roundId)==null) {
            throw new RoundNotFoundException();
        }
        roundMapper.deleteRoundByRoundId(roundId);
    }

    @Override
    public RoundScore getRoundScoreByRoundId(BigInteger roundScoreId) {
        return roundMapper.getRoundScoreByRoundId(roundScoreId);
    }

    @Override
    public RoundScore getRoundScoreByRoundIdAndTeamId(BigInteger roundId, BigInteger teamId) {
        return roundMapper.getRoundScoreByRoundIdAndTeamId(roundId,teamId);
    }

    @Override
    public void insertRoundScore(RoundScore roundScore) {
        roundMapper.insertRoundScore(roundScore);
    }

    @Override
    public void updateRoundScore(RoundScore roundScore) {
        roundMapper.updateRoundScore(roundScore);
    }

    @Override
    public void deleteRoundScoreByRoundScoreId(BigInteger roundScoreId) {
        roundMapper.deleteRoundScoreByRoundScoreId(roundScoreId);
    }

    @Override
    public int getMaxRoundSerialByCourseId(BigInteger courseId) {
        return roundMapper.getMaxRoundSerialByCourseId(courseId);
    }

    @Override
    public Round getRoundByCourseIdAndRoundSerial(BigInteger courseId,int roundSerial){
        return roundMapper.getRoundByCourseIdAndRoundSerial(courseId,roundSerial);
    }
}