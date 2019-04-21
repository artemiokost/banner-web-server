package ru.jarsoft.service;

import org.springframework.data.domain.Sort;
import ru.jarsoft.exception.BadRequestException;
import ru.jarsoft.exception.ContentNotFoundException;
import ru.jarsoft.model.Banner;
import ru.jarsoft.model.Category;
import ru.jarsoft.model.Request;
import ru.jarsoft.validation.BannerRequest;
import ru.jarsoft.repository.CategoryRepository;
import ru.jarsoft.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jarsoft.repository.RequestRepository;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

/**
 * Service class for {@link Banner}.
 *
 * @author Artem Kostritsa
 * @version 1.0
 */

@Service
public class BannerService {

    private final BannerRepository bannerRepository;
    private final CategoryRepository categoryRepository;
    private final RequestRepository requestRepository;

    @Autowired
    public BannerService(BannerRepository bannerRepository,
                         CategoryRepository categoryRepository,
                         RequestRepository requestRepository) {
        this.bannerRepository = bannerRepository;
        this.categoryRepository = categoryRepository;
        this.requestRepository = requestRepository;
    }

    @Transactional
    public Banner create(BannerRequest bannerRequest) {

        Category category = categoryRepository.findById(bannerRequest.getCategoryId())
                .orElseThrow(() -> new BadRequestException("Invalid categoryId"));

        Banner banner = new Banner()
                .setCategory(category)
                .setContent(bannerRequest.getContent())
                .setName(bannerRequest.getName())
                .setPrice(bannerRequest.getPrice());

        return bannerRepository.save(banner);
    }

    public Boolean existsByName(String name) {
        return bannerRepository.existsByName(name);
    }

    @Transactional
    public String getBidByCategory(String reqName, HttpServletRequest httpServletRequest){

        String ipAddress = httpServletRequest.getRemoteAddr();
        String userAgent = httpServletRequest.getHeader("User-Agent");

        List<Banner> bannerList = bannerRepository.findBySpecificRequest(ipAddress, userAgent, reqName);

        Banner banner = bannerList.stream()
                .max(Comparator.comparing(Banner::getPrice))
                .orElseThrow(ContentNotFoundException::new);

        Request request = new Request()
                .setIpAddress(ipAddress)
                .setUserAgent(userAgent)
                .setBanner(banner);

        requestRepository.save(request);

        return banner.getContent();
    }

    public List<Banner> getList(){
        return bannerRepository.findAllByDeleted(false, Sort.by(Sort.Order.asc("name")));
    }

    @Transactional
    public Banner updateById(int bannerId, BannerRequest bannerRequest) {

        Banner banner = bannerRepository.findById(bannerId)
                .orElseThrow(() -> new BadRequestException("Invalid bannerId"));

        Category category = categoryRepository.findById(bannerRequest.getCategoryId())
                .orElseThrow(() -> new BadRequestException("Invalid categoryId"));

        banner.setCategory(category)
                .setDeleted(bannerRequest.isDeleted())
                .setContent(bannerRequest.getContent())
                .setName(bannerRequest.getName())
                .setPrice(bannerRequest.getPrice());

        return bannerRepository.save(banner);
    }
}
