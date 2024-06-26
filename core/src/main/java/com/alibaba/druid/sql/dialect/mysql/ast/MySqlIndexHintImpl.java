/*
 * Copyright 1999-2017 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.druid.sql.dialect.mysql.ast;

import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlASTVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class MySqlIndexHintImpl extends MySqlObjectImpl implements MySqlIndexHint {
    private Option option;

    private List<SQLName> indexList = new ArrayList<SQLName>();

    @Override
    public abstract void accept0(MySqlASTVisitor visitor);

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public List<SQLName> getIndexList() {
        return indexList;
    }

    public void setIndexList(List<SQLName> indexList) {
        this.indexList = indexList;
    }

    public abstract MySqlIndexHintImpl clone();

    public void cloneTo(MySqlIndexHintImpl x) {
        x.option = option;
        for (SQLName name : indexList) {
            SQLName name2 = name.clone();
            name2.setParent(x);
            x.indexList.add(name2);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MySqlIndexHintImpl)) {
            return false;
        }
        MySqlIndexHintImpl that = (MySqlIndexHintImpl) o;
        return getOption() == that.getOption() && Objects.equals(getIndexList(), that.getIndexList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOption(), getIndexList());
    }
}
