/*
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package simple.crawler.mongo;

import simple.crawler.util.HttpURL;
import simple.crawler.util.MD5;
import simple.crawler.util.UriID;

import com.mongodb.DBObject;

/**
 * @author <a href="mailto:haithanh0809@gmail.com">Nguyen Thanh Hai</a>
 * @version $Id$
 *
 */
public class RawDBObject extends CrawlingDBObject
{
   public RawDBObject(String url, String html)
   {
      super(null);
      HttpURL httpUrl = new HttpURL(url);
      UriID uri = new UriID(httpUrl);
      put("_id", uri.toString());
      put("url", url);
      put("raw", html);
      put("hash", MD5.digest(html).toString());
      put("hostname", httpUrl.getHost());
      put("analyzed", false);
      put("extracted", false);
   }
   
   public RawDBObject(DBObject obj)
   {
      super(obj);
      put("_id", obj.get("_id"));
      put("url", obj.get("url"));
      put("raw", obj.get("raw"));
      put("hash", obj.get("hash"));
      put("hostname", obj.get("hostname"));
      put("analyzed", obj.get("analyzed") == null ? false : obj.get("analyzed"));
      put("extracted", obj.get("extracted") == null ? false : obj.get("extracted"));
   }
   
   public String getHtml()
   {
      return getString("raw");
   }
   
   public String getHash() 
   {
      return getString("hash");
   }
   
   public boolean isExtracted() {
      return getBoolean("extracted");
   }
   
   public boolean isAnalyzed() {
      return getBoolean("analyzed");
   }
   
   @Override
   public String getID()
   {
      return getString("_id");
   }

   @Override
   public String getURL()
   {
      return getString("url");
   }
}
