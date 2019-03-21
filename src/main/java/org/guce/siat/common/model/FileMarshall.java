package org.guce.siat.common.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.guce.siat.common.model.File;

@javax.persistence.Entity
@Table(
   name = "FILES_MARSHALL"
)
public class FileMarshall implements Serializable {

   private static final long serialVersionUID = 1L;
   @Id
   @OneToOne
   @JoinColumn(
      name = "FILE_ID"
   )
   private File file;
   @Lob
   @Column(
      name = "FILE_MARSHALL",
      length = 10000
   )
   private byte[] marshall;


   public FileMarshall() {}

   public FileMarshall(File file) {
      this.file = file;
   }

   public File getFile() {
      return this.file;
   }

   public void setFile(File file) {
      this.file = file;
   }

   public byte[] getMarshall() {
      return this.marshall;
   }

   public void setMarshall(byte[] marshall) {
      this.marshall = marshall;
   }

   public int hashCode() {
      byte hash = 5;
      int hash1 = 17 * hash + Objects.hashCode(this.file);
      hash1 = 17 * hash1 + Arrays.hashCode(this.marshall);
      return hash1;
   }

   public boolean equals(Object obj) {
      if(obj == null) {
         return false;
      } else if(this.getClass() != obj.getClass()) {
         return false;
      } else {
         FileMarshall other = (FileMarshall)obj;
         return Objects.equals(this.file, other.file);
      }
   }

   public String toString() {
      return "org.guce.siat.common.model.FileMarshall[ id=" + this.file.getId() + " ]";
   }
}
