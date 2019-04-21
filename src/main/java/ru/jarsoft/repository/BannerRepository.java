package ru.jarsoft.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import ru.jarsoft.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BannerRepository extends JpaRepository<Banner, Integer> {

    Boolean existsByName(String name);

    List<Banner> findAllByDeleted(Boolean bool, Sort sort);

    @Query(value = "select banner.* from (select request.banner_id, max(date) as last from request\n" +
            "where ip_address = ? and user_agent = ? group by banner_id) r\n" +
            "right join banner on r.banner_id = banner.id\n" +
            "right join category on banner.category_id = category.id\n" +
            "where banner.deleted is not true and category.req_name = ?\n" +
            "and (r.last is null or r.last <= now() - interval 1 day)", nativeQuery = true)
    List<Banner> findBySpecificRequest(String ipAddress, String userAgent, String reqName);
}
