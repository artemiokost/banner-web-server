package ru.jarsoft.controller;

import org.springframework.http.ResponseEntity;
import ru.jarsoft.model.Banner;
import ru.jarsoft.validation.BannerRequest;
import ru.jarsoft.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Banner controller.
 *
 * @author Artem Kostritsa
 * @version 1.0
 */

@RestController
@RequestMapping()
public class BannerController {

    private final BannerService bannerService;

    @Autowired
    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @GetMapping(value = "/exists/banner")
    public Boolean existsByName(@RequestParam(value = "name") String name) {
        return bannerService.existsByName(name);
    }

    @GetMapping(value = "/bid")
    public String getBidByCategory(@RequestParam(value = "category") String category,
                                   HttpServletRequest httpServletRequest) {
        return bannerService.getBidByCategory(category, httpServletRequest);
    }

    @GetMapping(value = "/list/banner")
    public List<Banner> getList() {
        return bannerService.getList();
    }

    @PostMapping(value = "/banner")
    public Banner create(@Valid @RequestBody BannerRequest bannerRequest) {
        return bannerService.create(bannerRequest);
    }

    @PutMapping(value = "/banner")
    public Banner updateById(@RequestParam(value = "bannerId") int bannerId,
                         @Valid @RequestBody BannerRequest bannerRequest) {
        return bannerService.updateById(bannerId, bannerRequest);
    }
}
