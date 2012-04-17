/*
 * 
 */
package com.damnhandy.uri.template;

import java.util.Map;

import com.damnhandy.uri.template.impl.RFC6570UriTemplate;

/**
 * 
 * A UriTemplate.
 * 
 * @author <a href="ryan@damnhandy.com">Ryan J. McDonough</a>
 * @version $Revision: 1.1 $
 */
public abstract class UriTemplate
{

   public static enum Modifier {

      NONE         ("na"),
      PREFIX       (":" ), 
      EXPLODE      ("*" ),
      DEFAULT_VALUE("=" );

      /**
       * 
       */
      private String value;

      /**
       * 
       * Create a new Modifier.
       * 
       * @param value
       */
      private Modifier(String value)
      {
         this.value = value;
      }

      /**
       * 
       * 
       * @return
       */
      public String getValue()
      {
         return value;
      }

      /**
       * 
       * 
       * @param value
       * @return
       */
      public static Modifier fromValue(String value)
      {
         for (Modifier m : Modifier.values())
         {
            if (m.getValue().equalsIgnoreCase(value))
            {
               return m;
            }
         }
         return null;
      }
   }
   /**
    * <p>
    * Represents an operator in a URI Template.
    * </p>
    */
   protected static enum Op {

      NONE         ("",  ",", false),
      RESERVED     ("+", ",", false), 
      FRAGMENT     ("#", ",", false), 
      NAME_LABEL   (".", "," ,false), 
      PATH         ("/", "/", false), 
      MATRIX       (";", "=", true), 
      QUERY        ("?", "&", true), 
      CONTINUATION ("&", "&", true);

      /**
       * 
       */
      private String operator;

      /**
       * 
       */
      private String joiner;
      /**
       * 
       */
      private boolean queryString;
      /**
       * 
       * Create a new Op.
       * 
       * @param operator
       * @param joiner
       */
      private Op(String operator, String joiner, boolean queryString)
      {
         this.operator = operator;
         this.joiner = joiner;
         this.queryString = queryString;
      }

      public String getOperator()
      {
         return this.operator;
      }

      public String getJoiner()
      {
         return this.joiner;
      }

      public boolean useQueryString()
      {
         return queryString;
      }
      
      /**
       * 
       * 
       * @return
       */
      public String getPrefix()
      {
         return operator;
      }
      /**
       * FIXME Comment this
       * 
       * @param opCode
       * @return
       */
      public static Op valueOfOpCode(String opCode)
      {
          for (Op op : Op.values())
          {
              if (op.getOperator().equalsIgnoreCase(opCode))
              {
                  return op;
              }
          }
          return null;
      }
   }
   /**
    * FIXME Comment this
    * 
    * @param template
    * @return
    */
   public static UriTemplate create(String template) {
      return new RFC6570UriTemplate(template);
   }
   /**
    * Expand the URI template using the supplied variables
    * 
    * @param vars The variables that will be used in the expansion
    * @return the expanded URI as a String
    */
   public abstract String expand(Map<String, Object> vars);

   /**
    * Returns the original URI template string.
    * 
    * @return
    */
   public abstract String getExpression();

}