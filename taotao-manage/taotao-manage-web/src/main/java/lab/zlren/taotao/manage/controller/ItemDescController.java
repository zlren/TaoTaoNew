package lab.zlren.taotao.manage.controller;

import lab.zlren.taotao.manage.pojo.ItemDesc;
import lab.zlren.taotao.manage.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ItemDescController 商品描述
 * Created by zlren on 17/2/15.
 */
@RequestMapping("/item/desc")
@Controller
public class ItemDescController {

    @Autowired
    private ItemDescService itemDescService;

    /**
     * 根据商品id查询商品描述数据
     *
     * @param itemId
     * @return
     */
    @RequestMapping(value = "{itemId}", method = RequestMethod.GET)
    public ResponseEntity<ItemDesc> queryByItemId(@PathVariable("itemId") Long itemId) {
        try {
            // itemId看作是itemdesc表的业务id
            ItemDesc itemDesc = this.itemDescService.queryById(itemId);

            if (null == itemDesc) {
                // 资源不存在
                // 404
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            // 200
            return ResponseEntity.ok(itemDesc);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 出错 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
