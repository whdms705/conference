package com.example.demo.meeting.service;

import com.example.demo.meeting.model.Conference;
import com.example.demo.meeting.model.ConferenceDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.beans.PropertyEditor;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ConferenceServiceTests {

    @Autowired
    ConferenceService conferenceService;

    BindingResult bindingResult = null;
    ConferenceDto conferenceDto = null;

    @Before
    public void setup(){
        conferenceDto = new ConferenceDto();
        conferenceDto.setStartTime("10:30");//4
        conferenceDto.setEndTime("11:30");//5
        conferenceDto.setConUser("eunho");
        conferenceDto.setConName("B");
        conferenceDto.setConDate("2019-11-10");

        bindingResult = new BindingResult() {
            @Override
            public Object getTarget() {
                return null;
            }

            @Override
            public Map<String, Object> getModel() {
                return null;
            }

            @Override
            public Object getRawFieldValue(String s) {
                return null;
            }

            @Override
            public PropertyEditor findEditor(String s, Class<?> aClass) {
                return null;
            }

            @Override
            public PropertyEditorRegistry getPropertyEditorRegistry() {
                return null;
            }

            @Override
            public String[] resolveMessageCodes(String s) {
                return new String[0];
            }

            @Override
            public String[] resolveMessageCodes(String s, String s1) {
                return new String[0];
            }

            @Override
            public void addError(ObjectError objectError) {

            }

            @Override
            public String getObjectName() {
                return null;
            }

            @Override
            public void setNestedPath(String s) {

            }

            @Override
            public String getNestedPath() {
                return null;
            }

            @Override
            public void pushNestedPath(String s) {

            }

            @Override
            public void popNestedPath() throws IllegalStateException {

            }

            @Override
            public void reject(String s) {

            }

            @Override
            public void reject(String s, String s1) {

            }

            @Override
            public void reject(String s, Object[] objects, String s1) {

            }

            @Override
            public void rejectValue(String s, String s1) {

            }

            @Override
            public void rejectValue(String s, String s1, String s2) {

            }

            @Override
            public void rejectValue(String s, String s1, Object[] objects, String s2) {

            }

            @Override
            public void addAllErrors(Errors errors) {

            }

            @Override
            public boolean hasErrors() {
                return false;
            }

            @Override
            public int getErrorCount() {
                return 0;
            }

            @Override
            public List<ObjectError> getAllErrors() {
                return null;
            }

            @Override
            public boolean hasGlobalErrors() {
                return false;
            }

            @Override
            public int getGlobalErrorCount() {
                return 0;
            }

            @Override
            public List<ObjectError> getGlobalErrors() {
                return null;
            }

            @Override
            public ObjectError getGlobalError() {
                return null;
            }

            @Override
            public boolean hasFieldErrors() {
                return false;
            }

            @Override
            public int getFieldErrorCount() {
                return 0;
            }

            @Override
            public List<FieldError> getFieldErrors() {
                return null;
            }

            @Override
            public FieldError getFieldError() {
                return null;
            }

            @Override
            public boolean hasFieldErrors(String s) {
                return false;
            }

            @Override
            public int getFieldErrorCount(String s) {
                return 0;
            }

            @Override
            public List<FieldError> getFieldErrors(String s) {
                return null;
            }

            @Override
            public FieldError getFieldError(String s) {
                return null;
            }

            @Override
            public Object getFieldValue(String s) {
                return null;
            }

            @Override
            public Class<?> getFieldType(String s) {
                return null;
            }
        };
    }


    @Test
    public void conference_전체데이터_조회(){
        List<Conference> list = conferenceService.selectAllConference();
        log.info("list : {}",list);
        assertNotNull(list);
    }

    @Test
    public void 예약시간_저장확인_테스트(){

        int id = conferenceService.insertConference(conferenceDto,bindingResult);
        log.info("id : {}",id);
        assertTrue(id > 0);

    }

    @Test
    public void 예약시간_conTime_바인딩_테스트(){

        String predictVal = "3,4,5,";

        Conference conference = conferenceService.convertConTime(conferenceDto);
        assertTrue(predictVal.equals(conference.getConTime()));
    }


    @Test
    public void 예약시간_30단위_포맷이_맞지않은_테스트(){
        conferenceDto.setEndTime("12:10");
        boolean result = conferenceService.checkFormatTime(conferenceDto);

        assertTrue(result);
    }

    @Test
    public void 예약시간_30단위_포맷_테스트(){
        boolean result = conferenceService.checkFormatTime(conferenceDto);

        assertTrue(!result);
    }


    @Test // 9시이전 19시 이후 체크
    public void 시간범위에_벗어난_체크_테스트(){
        conferenceDto.setEndTime("20:00");
        boolean result = conferenceService.checkFormatTime(conferenceDto);

        assertTrue(result);
    }

    @Test
    public void 동일한회의실_반복예약이없는경우_동일시간에_예약불가_테스트(){
        //given
        conferenceDto.setConDate("2019-11-17");
        String conTime = "3,4,5,6";

        // when
       boolean result  = conferenceService.isAvailReservation(conferenceDto,conTime);
       // true인 경우 예약불가

       // then
       assertTrue(result);
    }

    @Test
    public void 동일한회의실_반복예약이있는경우_동일시간에_예약불가_테스트(){
        //given
        conferenceDto.setConDate("2019-11-17");
        conferenceDto.setIsRepeat(2);
        String conTime = "3,4,";

        // when
        boolean result  = conferenceService.isAvailReservation(conferenceDto,conTime);

        // then
        assertTrue(result);
    }

}
