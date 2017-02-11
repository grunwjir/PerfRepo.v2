/**
 * PerfRepo
 * <p>
 * Copyright (C) 2015 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.perfrepo.web.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

/**
 * A binary file that can be attached to test execution.
 *
 * @author Michal Linhard (mlinhard@redhat.com)
 */
@javax.persistence.Entity
@Table(name = "test_execution_attachment")
@NamedQueries({
    @NamedQuery(name = TestExecutionAttachment.FIND_BY_EXECUTION, query = "SELECT attachment FROM TestExecutionAttachment attachment WHERE attachment.testExecution.id = :executionId")
})
public class TestExecutionAttachment implements Entity<TestExecutionAttachment> {

   private static final long serialVersionUID = -3358483095886229881L;
   public static final String FIND_BY_EXECUTION = "TestExecutionAttachment.findByExecution";

   /**
    * Constructor.
    */
   public TestExecutionAttachment() {
      super();
   }

   /**
    * Constructor.
    *
    * @param id
    * @param filename
    * @param mimetype
    */
   public TestExecutionAttachment(Long id, String filename, String mimetype) {
      super();
      this.id = id;
      this.filename = filename;
      this.mimetype = mimetype;
   }

   @Id
   @SequenceGenerator(name = "TEST_EXECUTION_ATTACHMENT_ID_GENERATOR", sequenceName = "TEST_EXECUTION_ATTACHMENT_SEQUENCE", allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEST_EXECUTION_ATTACHMENT_ID_GENERATOR")
   private Long id;

   @Column(name = "filename")
   @NotNull
   @Size(max = 2047)
   private String filename;

   @Column(name = "mimetype")
   @NotNull
   @Size(max = 255)
   private String mimetype;

   @ManyToOne(optional = false)
   @JoinColumn(name = "test_execution_id", referencedColumnName = "id")
   private TestExecution testExecution;

   @Lob
   @Column(name = "content")
   @NotNull
   @Size(max = 1048576)
   private byte[] content;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public void setTestExecution(TestExecution testExecution) {
      this.testExecution = testExecution;
   }

   public TestExecution getTestExecution() {
      return this.testExecution;
   }

   public String getFilename() {
      return filename;
   }

   public void setFilename(String filename) {
      this.filename = filename;
   }

   public String getMimetype() {
      return mimetype;
   }

   public void setMimetype(String mimetype) {
      this.mimetype = mimetype;
   }

   public byte[] getContent() {
      return content;
   }

   public void setContent(byte[] content) {
      this.content = content;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof TestExecutionAttachment)) return false;

      TestExecutionAttachment that = (TestExecutionAttachment) o;

      if (getFilename() != null ? !getFilename().equals(that.getFilename()) : that.getFilename() != null) return false;
      if (getMimetype() != null ? !getMimetype().equals(that.getMimetype()) : that.getMimetype() != null) return false;
      return Arrays.equals(getContent(), that.getContent());
   }

   @Override
   public int hashCode() {
      int result = getFilename() != null ? getFilename().hashCode() : 0;
      result = 31 * result + (getMimetype() != null ? getMimetype().hashCode() : 0);
      result = 31 * result + Arrays.hashCode(getContent());
      return result;
   }

   @Override
   public String toString() {
      return "TestExecutionAttachment{" +
              "filename='" + filename + '\'' +
              ", id=" + id +
              ", mimetype='" + mimetype + '\'' +
              '}';
   }
}