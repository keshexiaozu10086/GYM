package cn.njust.dao;

import cn.njust.Service.Customer.RentService;
import cn.njust.entity.Venue;
import cn.njust.entity.Order;
import cn.njust.entity.User;
import cn.njust.entity.Venue;
import cn.njust.utils.DBUtil;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VenueDao extends BaseDao{
    /**
     * 查询场地,返回场地信息
     */
    public static List<Venue> findAllVenue(){
        String sql = "select * from venue";
        List<Map<String, Object>> list = null;
        List<Venue> venues=new ArrayList<Venue>();
        try {
            list = DBUtil.query(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Map<String, Object> i:list)
        {
            Venue j=new Venue();
            j.setId(i.get("venue_id").toString());
            j.setType(i.get("venue_type").toString());
            j.setName(i.get("venue_name").toString());
            j.setPrice(Integer.parseInt(i.get("venue_price").toString()));
            j.setState(i.get("venue_state").toString());
            venues.add(j);

        }
        //System.out.println(list.get(0).get("venue_state"));
        return venues;//返回场地信息
    }

    /**
     *   输入venue,实现场地信息删除
     */
    public static void deleteVenueById(Venue venue) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("venue_id", venue.getId());//根据id寻找进而删除
        try {
            int count = DBUtil.delete("venue", whereMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 输入venue实现更新，可变更state,price
     */
    public static void updateVenue(Venue venue) {
        Map<String, Object> map = new HashMap<>();
        map.put("venue_price",venue.getPrice() );//更新价格
        map.put("venue_state",venue.getState() );//更新状态
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("venue_id", venue.getId());//根据id寻找
        try {
            int count = DBUtil.update("venue", map, whereMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static int findPriceByName(String vName)
    {
        String sql = "select venue_price from venue where venue_name="+ vName;
        int sum=0;
        try {
            List<Map<String, Object>> lis = DBUtil.query(sql);
                Map<String, Object> ma=lis.get(0);
                sum= (int) ma.get("venue_price");
              //System.out.println(sum);
                return sum;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
           // System.out.println(sum);
            return sum;
        }
    }

    public static Venue findVenueByVenueId(String vid)
    {
        String sql = "select * from venue where venue_id=" +vid;
        try {
            List<Map<String, Object>> lis = DBUtil.query(sql);
            if(lis.isEmpty()) return null;
            else
            {
                Map<String, Object> ma=lis.get(0);
                return new Venue(ma.get("venue_id").toString(),ma.get("venue_name").toString(),ma.get("venue_type").toString(),Integer.parseInt(ma.get("venue_price").toString()),ma.get("venue_state").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void insertVenue(Venue venue) {
        Map<String, Object> map = new HashMap<>();
        map.put("venue_name",venue.getName());
        map.put("venue_id", venue.getId());
        map.put("venue_price",venue.getPrice() );
        map.put("venue_state",venue.getState() );
        map.put("venue_type",venue.getType() );
        try {
            DBUtil.insert("venue",map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//   public static void main(String[] args){
//        Venue x=new Venue("931","54","48",2,"8");
//        VenueDao.updateVenue(x);
//
//    }


   /* public VenueDao findVenue(Venue venue)
    {
        try {
            super.connect();
            String sql = "select *from venue where venue_name=? and venue_type=? and venue_state=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, venue.getName());
            pstmt.setString(2, venue.getType());
            pstmt.setInt(3, venue.getState());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Venue e = new Venue();
                e.setId(rs.getString("venue_id"));
                e.setName(rs.getString("venue_name"));
                e.setPrice(rs.getInt("venue_price"));
                e.setType(rs.getString("venue_type"));
                e.setState(rs.getInt("venue_state"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return null;
    }
    public void addVenue(Venue venue) {
        try {
            super.connect();
            String sql = "insert into venue(venue_id,venue_name,venue_type,venue_price,venue_state) values(?,?,?,?,?)";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, venue.getId());
            pstmt.setString(2, venue.getName());
            pstmt.setString(3, venue.getType());
            pstmt.setInt(4, venue.getPrice());
            pstmt.setInt(5, venue.getState());
            pstmt.execute();
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally{
            super.closeAll();
        }
    }
    public void deleteVenue(Venue venue)
    {
        try {
            super.connect();
            String sql="delete from venue where venue_id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,venue.getId());
            pstmt.execute();
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally{
            super.closeAll();
        }
    }
    public void updateVenue(Venue venue)
    {
        try {
            super.connect();
            String sql="update venue set venue_state=? and venue_price=? where venue_id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,venue.getPrice());
            pstmt.setInt(2,venue.getState());
            pstmt.setString(3,venue.getId());
            pstmt.execute();
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally{
            super.closeAll();
        }
    }*/
}
