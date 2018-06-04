package me.yanglw.android.spi.demo.biz4.service.commodity;

import me.yanglw.android.spi.ServiceProvider;

/**
 * Created by yanglw on 2018-05-28.
 */
@ServiceProvider(services = {Biz4CommodityService.class})
public class Biz4CommodityServiceProvider implements Biz4CommodityService {
    @Override
    public Biz4Commodity getComodityById(String s) {
        Biz4Commodity commodity = new Biz4Commodity();
        commodity.id = s;
        commodity.name = "PS4 Pro";
        commodity.desp = "Sony 给你多少钱，我 Microsoft 给你十倍。";
        return commodity;
    }
}
