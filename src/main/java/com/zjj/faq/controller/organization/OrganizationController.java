package com.zjj.faq.controller.organization;

import com.zjj.faq.batis.utils.Msg;
import com.zjj.faq.controller.organization.request.CreateOrgRequest;
import com.zjj.faq.service.outer.organization.OrganizationService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 阿呆的小鸡仔
 */
@RestController
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping("/organization")
    public Msg getOrganization(HttpServletRequest request){
        return Msg.success().add("data",organizationService.getOrganization(request));
    }

    @PostMapping("/organization")
    public Msg createOrganization(@RequestBody CreateOrgRequest createOrgRequest, HttpServletRequest request){
        return organizationService.createOrganization(createOrgRequest,request)?Msg.success():Msg.fail().add("info","系统出错，请稍后再试");
    }

    @DeleteMapping("/organization/{id}")
    public Msg getOrganization(@PathVariable Long id){
        return organizationService.deleteOrganization(id)?Msg.success():Msg.fail().add("info","系统出错，请稍后再试");

    }

    @GetMapping("/organization/{idOrName}")
    public Msg searchOrganizationByIdOrName(@PathVariable String idOrName){
        return Msg.success().add("result",organizationService.searchOrganization(idOrName));
    }

    @PostMapping("/joinOrganization/{organizationId}")
    public Msg joinOrganization(@PathVariable Long organizationId,HttpServletRequest request){
        return organizationService.joinOrganization(organizationId,request)?Msg.success():Msg.fail().add("info","系统出错，请稍后再试");
    }

    @GetMapping("/joinOrganization")
    public Msg getWithOrganizationJoin(HttpServletRequest request){
        return Msg.success().add("result",organizationService.getWithOrganizationJoin(request));
    }

}
