package com.jisutudy.web;

import com.jisutudy.service.CustService;
import com.jisutudy.web.dto.CustListResponseDto;
import com.jisutudy.web.dto.CustSaveRequestDto;
import com.jisutudy.web.dto.CustUpdateRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CustApiController", description = "고객 관련 rest api")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/custs")
public class CustApiController {

    private final CustService custService;

    @Operation(summary = "고객 전체 조회")
    @GetMapping("")
    public List<CustListResponseDto> findAllCust() {
        System.out.println("컨트롤러메서드호출");
//        System.out.println("Retrieved data: " + jpaCustService.findAll());
        List<CustListResponseDto> list = custService.findAll();
        System.out.println("***************** 확인=> " + list.getClass());
        return list;
    }

    @Operation(summary = "고객 등록")
    @PostMapping("")
    public Long save(@RequestBody @Valid CustSaveRequestDto requestDto, BindingResult result) {
//        if (result.hasErrors()) {
//            return "cust-createForm";
//        }

        Long custId = custService.save(requestDto);
        return custId;
    }

    @Operation(summary = "고객 단건 조회")
    @GetMapping("/{id}")
    public CustListResponseDto findById(@PathVariable("id") Long id) {
        return custService.findById(id);
    }

    @Operation(summary = "고객 단건 수정")
    @PutMapping("/{id}")
    public Long update(@PathVariable("id") Long id, @RequestBody CustUpdateRequestDto requestDto) {
        return custService.update(id, requestDto);
    }

    @Operation(summary = "전화번호로 고객 찾기")
    @PostMapping("/search")
    public CustListResponseDto findByPhoneNumber(@RequestBody String phoneNumber) {
        return custService.findByPhoneNumber(phoneNumber);
    }


}
