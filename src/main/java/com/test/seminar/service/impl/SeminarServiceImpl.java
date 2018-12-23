package com.test.seminar.service.impl;

import com.test.seminar.dao.CourseClassDao;
import com.test.seminar.dao.PresentationDao;
import com.test.seminar.dao.RoundDao;
import com.test.seminar.dao.SeminarDao;
import com.test.seminar.entity.*;
import com.test.seminar.exception.HaveEnrollException;
import com.test.seminar.exception.RepetitiveRecordException;
import com.test.seminar.exception.SeminarControlNotFoundException;
import com.test.seminar.exception.SeminarInfoNotFoundException;
import com.test.seminar.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeminarServiceImpl implements SeminarService {

    @Autowired
    private SeminarDao seminarDao;
    @Autowired
    private RoundDao roundDao;
    @Autowired
    private CourseClassDao courseClassDao;
    @Autowired
    private PresentationDao presentationDao;

    @Override
    public SeminarInfo getSeminarInfoBySeminarInfoId(BigInteger seminarInfoId) throws SeminarInfoNotFoundException {
        return seminarDao.getSeminarInfoBySeminarInfoId(seminarInfoId);
    }

    @Override
    public void insertSeminarInfo(SeminarInfo seminarInfo,BigInteger courseId,BigInteger roundId) throws RepetitiveRecordException {
        if(roundId.equals(new BigInteger("-1")))
        {
            Round round = new Round();
            round.setRoundSerial(roundDao.getMaxRoundSerialByCourseId(courseId)+1);
            roundDao.insertRound(round,courseId);
            round=roundDao.getRoundByCourseIdAndRoundSerial(courseId,round.getRoundSerial());
            roundId=round.getId();
        }
        seminarDao.insertSeminarInfo(seminarInfo,roundId,courseId);
        seminarInfo = seminarDao.getSeminarInfoBySeminarNameAndCourseId(seminarInfo.getSeminarName(),courseId);
        List<CourseClass>courseClassList = courseClassDao.getCourseClassByCourseId(courseId);
        for(CourseClass courseClass:courseClassList){
            SeminarControl seminarControl = new SeminarControl();
            seminarDao.insertSeminarControl(seminarControl,courseClass.getId(),seminarInfo.getId());
        }
    }

    @Override
    public void updateSeminarInfoBySeminarInfoId(SeminarInfo seminarInfo,BigInteger roundId) throws SeminarInfoNotFoundException {
        seminarDao.updateSeminarInfo(seminarInfo,roundId);
    }

    @Override
    public SeminarControl getSeminarControlBySeminarControlId(BigInteger seminarControlId)
    {
        return seminarDao.getSeminarControlBySeminarControlId(seminarControlId);
    }

    @Override
    public void deleteSeminarInfoBySeminarInfoId(BigInteger seminarInfoId) throws SeminarInfoNotFoundException {
        seminarDao.deleteSeminarInfoBySeminarInfoId(seminarInfoId);
    }

    @Override
    public SeminarControl getSeminarControlByClassIdAndSeminarInfoId(BigInteger classId, BigInteger seminarInfoId) throws SeminarControlNotFoundException {
        return seminarDao.getSeminarControlByClassIdAndSeminarInfo(classId,seminarInfoId);
    }

    @Override
    public List<SeminarInfo> getSeminarInfoByRoundId(BigInteger roundId){
        return seminarDao.getSeminarInfoByRoundId(roundId);
    }

    @Override
    public List<List<SeminarInfo>> getSeminarInfoByRoundList(List<Round> roundList) {
        List<List<SeminarInfo>> SeminarInfoOrderByRoundId= new ArrayList<List<SeminarInfo>>();
        List<SeminarInfo> temp;
        for(int i=0;i<roundList.size();i++)
        {
            temp=getSeminarInfoByRoundId(roundList.get(i).getId());
            SeminarInfoOrderByRoundId.add(i,temp);
        }
        return SeminarInfoOrderByRoundId;
    }

    @Override
    public void insertPresentation(int teamOrder,BigInteger seminarControlId, BigInteger teamId) throws HaveEnrollException{
        Presentation presentation = new Presentation();
        presentation.setTeamOrder(teamOrder);
        if(presentationDao.getPresentationByTeamOrder(teamOrder)!=null)
            throw new HaveEnrollException();
        presentationDao.deletePresentationBySeminarControlIdAndTeamId(seminarControlId,teamId);
        presentationDao.insertPresentation(presentation,seminarControlId,teamId);
    }
}
