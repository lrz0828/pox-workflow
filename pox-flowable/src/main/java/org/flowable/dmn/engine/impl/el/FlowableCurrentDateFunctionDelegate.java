/* Licensed under the Apache License, Version 2.0 (the "License");
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

package org.flowable.dmn.engine.impl.el;

import java.lang.reflect.Method;

import org.flowable.common.engine.impl.el.AbstractFlowableFunctionDelegate;
import org.flowable.dmn.engine.impl.el.util.DateUtil;

/**
 * A date function mapper that can be used in EL expressions
 * 
 * @author Tijs Rademakers
 */
public class FlowableCurrentDateFunctionDelegate extends AbstractFlowableFunctionDelegate {

    @Override
    public String prefix() {
        return "date";
    }

    @Override
    public String localName() {
        return "now";
    }

    @Override
    public Class<?> functionClass() {
        return DateUtil.class;
    }

    @Override
    public Method functionMethod() {
        return getNoParameterMethod();
    }

}
