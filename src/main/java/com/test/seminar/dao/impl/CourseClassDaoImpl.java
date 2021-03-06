package com.test.seminar.dao.impl;

import com.test.seminar.dao.CourseClassDao;
import com.test.seminar.entity.CourseClass;
import com.test.seminar.exception.CourseClassNotFoundException;
import com.test.seminar.exception.RepetitiveRecordException;
import com.test.seminar.mapper.CourseClassMapper;
import com.test.seminar.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @author zhenweiwang
 * @date 2018/11/29
 */
@Component
public class CourseClassDaoImpl implements CourseClassDao {
    @Autowired
    private CourseClassMapper courseClassMapper;

    @Override
    public CourseClass getCourseClassByCourseClassId(BigInteger courseClassId) throws CourseClassNotFoundException {
        CourseClass courseClass=courseClassMapper.getCourseClassByCourseClassId(courseClassId);
        if(courseClass==null){
            throw new CourseClassNotFoundException();
        }
        return courseClass;
    }

    @Override
    public void insertCourseClass(CourseClass courseClass) throws RepetitiveRecordException {
        courseClassMapper.insertCourseClass(courseClass);
    }

    @Override
    public void updateCourseClassByCourseClassId(CourseClass courseClass) throws CourseClassNotFoundException{
        if(courseClassMapper.getCourseClassByCourseClassId(courseClass.getId())==null) {
            throw new CourseClassNotFoundException();
        }
        courseClassMapper.updateCourseClassByCourseClassId(courseClass);
    }

    @Override
    public void deleteCourseClassByCourseClassId(BigInteger courseClassId) throws CourseClassNotFoundException{
        if(courseClassMapper.getCourseClassByCourseClassId(courseClassId)==null) {
            throw new CourseClassNotFoundException();
        }
        courseClassMapper.deleteCourseClassByCourseClassId(courseClassId);
    }

    @Override
    public List<CourseClass> getCourseClassByCourseId(BigInteger courseId) {
        return courseClassMapper.getCourseClassByCourseId(courseId);
    }

    @Override
    public CourseClass getCourseClassByStudentIdAndCourseId(BigInteger studentId, BigInteger courseId)throws CourseClassNotFoundException{
        CourseClass courseClass=courseClassMapper.getCourseClassByStudentIdAndCourseId(studentId,courseId);
        if(courseClass==null) {
            throw new CourseClassNotFoundException();
        }
        return courseClass;
    }

    @Override
    public void insertCourseClassStudentRelation(BigInteger courseClassId, BigInteger studentId, BigInteger courseId) {
        courseClassMapper.insertCourseClassStudentRelation(courseClassId,studentId,courseId);
    }
}
