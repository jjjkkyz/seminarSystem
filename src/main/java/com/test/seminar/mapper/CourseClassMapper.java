package com.test.seminar.mapper;

import com.test.seminar.entity.CourseClass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @author zhenweiwang
 * @date 2018/11/29
 */
@Mapper
@Component
public interface CourseClassMapper {

    /**
     * 通过ID获取班级信息
     *
     * @param courseClassId
     * @return
     */
    CourseClass getCourseClassByCourseClassId(BigInteger courseClassId);

    /**
     * 创建新的班级账户
     *
     * @param courseClass
     * @return
     */
    void insertCourseClass(CourseClass courseClass);

    /**
     * 更改班级信息
     *
     * @param courseClass
     * @return
     */
    void updateCourseClassByCourseClassId(CourseClass courseClass);

    /**
     * @param courseClassId
     * @return
     */
    void deleteCourseClassByCourseClassId(BigInteger courseClassId);

    /**
     * 获取某课程下的所有班级
     *
     * @param courseId
     * @return
     */
    List<CourseClass> getCourseClassByCourseId(BigInteger courseId);


}