package com.test.seminar.service.impl;

import com.test.seminar.dao.SeminarDao;
import com.test.seminar.entity.Round;
import com.test.seminar.entity.SeminarControl;
import com.test.seminar.entity.SeminarInfo;
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

    @Override
    public SeminarInfo getSeminarInfoBySeminarInfoId(BigInteger seminarInfoId) throws SeminarInfoNotFoundException {
        return seminarDao.getSeminarInfoBySeminarInfoId(seminarInfoId);
    }

    @Override
    public void insertSeminarInfo(SeminarInfo seminarInfo) throws RepetitiveRecordException {
        seminarDao.insertSeminarInfo(seminarInfo);
    }

    @Override
    public void updateSeminarInfoBySeminarInfoId(SeminarInfo seminarInfo) throws SeminarInfoNotFoundException {
        seminarDao.updateSeminarInfo(seminarInfo);
    }

    @Override
    public void deleteSeminarInfoBySeminarInfoId(BigInteger seminarInfoId) throws SeminarInfoNotFoundException {
        seminarDao.deleteSeminarInfoBySeminarInfoId(seminarInfoId);
    }

    @Override
    public SeminarControl getSemniarControlByClassIdAndSeminarInfoId(BigInteger classId, BigInteger seminarInfoId) throws SeminarControlNotFoundException {
        return seminarDao.getSemniarControlByClassIdAndSeminarInfo(classId,seminarInfoId);
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
}
