package com.handsome.didi.Controller;

import com.handsome.didi.Base.BaseApplication;
import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Address;
import com.handsome.didi.Bean.AddressDao;

import java.util.List;

/**
 * @author 许英俊 2017/4/27
 */
public class AddressController extends BaseController {

    public static AddressController addressController;

    public static AddressController getInstance() {
        if (addressController == null) {
            synchronized (AddressController.class) {
                if (addressController == null) {
                    addressController = new AddressController();
                }
            }
        }
        return addressController;
    }


    /**
     * 添加数据
     */
    public long insert(Address address) {
        return BaseApplication.getDaoInstant().getAddressDao().insert(address);
    }

    /**
     * 删除数据
     */
    public void delete(long id) {
        BaseApplication.getDaoInstant().getAddressDao().deleteByKey(id);
    }

    /**
     * 更新数据
     */
    public void update(Address address) {
        BaseApplication.getDaoInstant().getAddressDao().update(address);
    }

    /**
     * 查询指定数据，条件用户名必须相同
     */
    public List<Address> query(String username) {
        return BaseApplication.getDaoInstant().getAddressDao().queryBuilder().where(AddressDao.Properties.Username.eq(username)).list();
    }

    /**
     * 更新所有的地址，取消默认
     */
    public void updateAddressWithoutDefault(String username) {
        List<Address> query = query(username);
        for (Address address : query) {
            address.isdefault = false;
            update(address);
        }
    }

    /**
     * 查询默认地址
     *
     * @param username
     */
    public Address queryDefaultAddress(String username) {
        List<Address> query = query(username);
        for (Address address : query) {
            if (address.isdefault) {
                return address;
            }
        }
        return null;
    }

}
