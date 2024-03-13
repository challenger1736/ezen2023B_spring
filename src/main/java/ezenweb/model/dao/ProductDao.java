package ezenweb.model.dao;

import ezenweb.model.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDao extends SuperDao {
    // # 1. 등록 서비스/기능처리 요청
    public boolean postProductRegister( ProductDto productDto){System.out.println("ProductDao.postProductRegister");
        System.out.println("productDto = " + productDto);
        try{
            // 1. 제품 등록
            String sql = "insert into product (pname,pprice,pcontent,plat,plng,mno)value(?,?,?,?,?,?)";
            ps = conn.prepareStatement( sql , Statement.RETURN_GENERATED_KEYS ); // 이미지 등록시 제품번호 필요
            ps.setString( 1 , productDto.getPname() );      ps.setInt( 2 , productDto.getPprice() );
            ps.setString( 3 , productDto.getPcontent() );   ps.setString( 4 , productDto.getPlat() );
            ps.setString( 5 , productDto.getPlng() );       ps.setInt( 6 , productDto.getMno() );
            int count = ps.executeUpdate();
            if( count == 1 ) {
                // 2. 이미지 등록
                rs = ps.getGeneratedKeys(); // pk번호 호출
                if( rs.next() ){
                    // 등록할 이미지 개수만큼 SQL 실행
                    productDto.getPimg().forEach( (pimg )-> {
                        try{
                            String subSql = "insert into productimg( pimg , pno ) values( ? , ? );";
                            ps = conn.prepareStatement( subSql );
                            ps.setString( 1 , pimg );       ps.setInt( 2 , rs.getInt( 1 ) );
                            ps.executeUpdate();
                        }catch (Exception e ){   System.out.println("e = " + e);  }
                    });
                    return true;
                }
            }
        }catch (Exception e ){   System.out.println("e = " + e);   }
        return false;
    }

    // # 2. 제품 출력( 지도에 출력할 ) 요청
    public List<ProductDto> getProductList(){ System.out.println("ProductDao.getProductList");
        List<ProductDto> list = new ArrayList<>();
        try{
            String sql = "select * from product p inner join member m on p.mno = m.no;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while ( rs.next() ){ // 빌더패턴 : 클래스명.Builder().필드명(값).필드명(값).build()
                // - 제품
                ProductDto productDto = ProductDto.builder()
                        .pno( rs.getInt("pno") )
                        .pname( rs.getString("pname"))
                        .pprice( rs.getInt("pprice"))
                        .pstate( rs.getByte("pstate"))
                        .plat( rs.getString("plat"))
                        .plng( rs.getString("plng"))
                        .pdate( rs.getString("pdate"))
                        .mid( rs.getString("id"))
                        .pcontent( rs.getString("pcontent"))
                        .build() ;
                List<String> imgList = new ArrayList<>(); // - 제품 이미지
                // 제품 이미지 출력
                String subSql = "select * from productImg where pno = "+productDto.getPno();
                ps = conn.prepareStatement( subSql );
                ResultSet rs2 = ps.executeQuery();
                while ( rs2.next() ){
                    imgList.add(  rs2.getString("pimg") ) ;
                } // w end

                productDto.setPimg( imgList ); // 제품 이미지 등록

                list.add( productDto ); // 제품 목록에 제품 등록 ;

            }
        }catch (Exception e ){}
        return list;
    }
}